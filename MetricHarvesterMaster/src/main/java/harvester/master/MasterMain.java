package harvester.master;

import harvester.core.conversation.Conversation;
import harvester.core.conversation.ConversationProvider;
import harvester.core.message.Message;
import java.util.Calendar;

public class MasterMain {

    public static void main ( String[] args )
            throws InterruptedException {
        Thread sendingThread = new Thread( createSendingThread() );
        Thread listeningThread = new Thread( createListeningThread() );

        sendingThread.setDaemon( true );
        listeningThread.setDaemon( true );

        listeningThread.start();
        sendingThread.start();

        Thread.sleep( 26000 );
    }
    private static Conversation conversation = ConversationProvider.createSimpleConversation();

    private static Runnable createSendingThread () {
        return new Runnable() {
            private int counter = 20;

            public void run () {

                while ( counter > 0 ) {

                    Message message = createMessage();
                    message.setSendRequest( Calendar.getInstance().getTime() );
                    conversation.sendMessage( message, "requestQueue" );

                    counter--;
                    try {
                        Thread.sleep( 10000 );
                    } catch ( InterruptedException ex ) {
                        System.out.println( ex );
                    }
                }
            }

            private Message createMessage () {
                Message msg = new Message();
                msg.setCommand( "CPUPercent" );
                return msg;
            }
        };
    }

    private static Runnable createListeningThread () {
        return new Runnable() {
            private final static String QUEUE_NAME = "responseQueue";

            public void run () {
                try {
                    while ( true ) {
                        Message message = conversation.reciveMessage( QUEUE_NAME );
                        message.setReciveResponse( Calendar.getInstance().getTime() );
                        System.out.println( " [x][Master] Received '" + message + "'" );
                    }
                } catch ( Exception ex ) {
                    System.out.println( ex );
                }
            }
        };
    }
}