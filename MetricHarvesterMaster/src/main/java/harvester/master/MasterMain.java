package harvester.master;

import harvester.core.Configuration;
import harvester.core.conversation.Conversation;
import harvester.core.conversation.ConversationProvider;
import harvester.core.message.Message;
import java.net.UnknownHostException;
import java.util.Calendar;

public class MasterMain {

    public static void main ( String[] args )
            throws InterruptedException,
                   UnknownHostException {

        Thread sendingThread = new Thread( createSendingThread() );
        Thread listeningThread = new Thread( createListeningThread() );

        sendingThread.setDaemon( true );
        listeningThread.setDaemon( true );

        listeningThread.start();
        sendingThread.start();

        Thread.sleep( 60000 );
    }
    
    private static Conversation conversation = ConversationProvider.createSimpleConversation();

    private static Runnable createSendingThread () {
        return new Runnable() {
            public void run () {

                while ( true ) {

                    Message message = createMessage();

                    System.out.println( "Sending message: " + message );

                    conversation.sendMessage( message, Configuration.RABBIT_MQ.CLIENT_REGISTRATION_QUEUE );

                    try {
                        Thread.sleep( 5000 );
                    } catch ( InterruptedException ex ) {
                        System.out.println( ex );
                    }
                }
            }

            private Message createMessage () {
                Message msg = new Message();

                //msg.setRequest( "CPUPercent" );
                msg.setRequest( "FQDN" );

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
}