package harvester.master;

import harvester.core.Configuration;
import harvester.core.conversation.Conversation;
import harvester.core.conversation.ConversationProvider;
import harvester.core.message.Message;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class MasterMain {

    private static Conversation conversation = ConversationProvider.createSimpleConversation();
    private static Set<String> clientsNames;

    public static void main ( String[] args )
            throws InterruptedException,
                   UnknownHostException {

        Thread sendingThread = new Thread( createSendingThread() );
        Thread listeningThread = new Thread( createListeningThread() );
        Thread registrationThread = new Thread( createRegistrationListeningThread() );

        sendingThread.setDaemon( true );
        listeningThread.setDaemon( true );
        registrationThread.setDaemon( true );

        listeningThread.start();
        sendingThread.start();
        registrationThread.start();

        sleeping();
    }

    private static void sleeping () {
        try {
            Thread.sleep( 1000 * 60 * 3 );
        } catch ( InterruptedException ex ) {
            System.out.println( ex );
        }
    }

    private static Runnable createSendingThread () {
        return new Runnable() {
            public void run () {

                while ( true ) {
                    while ( clientsNames == null || clientsNames.isEmpty() ) {
                        try {
                            Thread.sleep( 5000 );
                        } catch ( InterruptedException ex ) {
                            System.out.println( ex );
                        }
                    }
                    Message message = createMessage();

                    System.out.println( "Sending message: " + message );

                    for ( String name : clientsNames ) {
                        conversation.sendMessage( message, name );
                    }

                    try {
                        Thread.sleep( 5000 );
                    } catch ( InterruptedException ex ) {
                        System.out.println( ex );
                    }
                }
            }

            private Message createMessage () {
                Message msg = new Message();

                msg.setRequest( "CPUPercent" );
                //msg.setRequest( "FQDN" );

                msg.setSendRequest( Calendar.getInstance().getTime() );
                return msg;
            }
        };
    }

    private static Runnable createListeningThread ()
            throws UnknownHostException {

        //final DatabaseManager manager = new DatabaseManager();

        return new Runnable() {
            public void run () {

                clientsNames = new CopyOnWriteArraySet<String>();

                try {
                    while ( true ) {
                        Message message = conversation.reciveMessage( Configuration.RABBIT_MQ.CLIENT_RESPONSE_QUEUE );
                        message.setReciveResponse( Calendar.getInstance().getTime() );

                        System.out.println( "Message recived: " + message );
                        //manager.getDatastore().save( message );
                    }
                } catch ( Exception ex ) {
                    System.out.println( ex );
                }
            }
        };
    }

    private static Runnable createRegistrationListeningThread ()
            throws UnknownHostException {

        return new Runnable() {
            public void run () {
                try {
                    while ( true ) {
                        Message message = conversation.reciveMessage( Configuration.RABBIT_MQ.CLIENT_REGISTRATION_QUEUE );

                        clientsNames.add( message.getRequest() );

                        System.out.println( "Slave registered: " + message.getRequest() );
                    }
                } catch ( Exception ex ) {
                    System.out.println( ex );
                }
            }
        };
    }
}