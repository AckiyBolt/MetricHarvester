package harvester.master;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.query.Query;
import harvester.configuration.Configuration;
import harvester.core.conversation.Conversation;
import harvester.core.conversation.ConversationProvider;
import harvester.core.message.Message;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import harvester.model.db.DatastoreManager;
import harvester.model.entity.Client;
import harvester.model.entity.Metric;
import java.util.List;

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

        System.out.println( "All agents were started. Main thread going to be sleeped." );
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

                System.out.println( "Sending agent ready for work" );
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

        return new Runnable() {
            public void run () {

                System.out.println( "Listening agent ready for work" );
                DatastoreManager manager = new DatastoreManager();
                Datastore datastore = manager.getDatastore();
                clientsNames = new CopyOnWriteArraySet<String>();

                while ( true ) {
                    Message message = conversation.reciveMessage( Configuration.RABBIT_MQ.CLIENT_RESPONSE_QUEUE );
                    message.setReciveResponse( Calendar.getInstance().getTime() );

                    System.out.println( "Message recived: " + message );

                    datastore.save( message );
                    Client client = datastore.find( Client.class, "name", message.getSenderName() ).get();
                    Metric metric = convertMessageToMetric( message );
                    client.getMetrics().add( metric );
                    datastore.merge( client );

                    System.out.println( "Message has been saved" );
                }

            }

            private Metric convertMessageToMetric ( Message message ) {
                Metric metric = new Metric();
                metric.setClientName( message.getSenderName() );
                metric.setMetric( message.getRequest() );
                metric.setResult( message.getResponse() );
                metric.setSendRequest( message.getSendRequest() );
                metric.setSendResponse( message.getSendResponse() );
                metric.setReciveRequest( message.getReciveRequest() );
                metric.setReciveResponse( message.getReciveResponse() );
                return metric;
            }
        };
    }

    private static Runnable createRegistrationListeningThread ()
            throws UnknownHostException {

        return new Runnable() {
            public void run () {
                
                System.out.println( "Registration listening agent ready for work" );
                
                while ( true ) {
                    Message message = conversation.reciveMessage( Configuration.RABBIT_MQ.CLIENT_REGISTRATION_QUEUE );
                    String clientName = message.getRequest();

                    Client client = loadClientOrCreateIfNotExist( clientName );

                    clientsNames.add( clientName );

                    System.out.println( "Slave registered: " + clientName );
                }
            }

            private Client loadClientOrCreateIfNotExist ( String clientName ) {

                DatastoreManager manager = new DatastoreManager();
                Datastore datastore = manager.getDatastore();

                Query<Client> resultQuery = datastore.find( Client.class, "name", clientName );
                List<Client> resultList = resultQuery.asList();

                Client client = null;

                if ( !resultList.isEmpty() )
                    client = resultList.get( 0 );
                else {
                    client = new Client();
                    client.setName( clientName );

                    datastore.save( client );
                }

                return client;
            }
        };
    }
}