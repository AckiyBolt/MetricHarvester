package harvester.master;

import com.google.gson.Gson;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MasterMain {

    public static void main ( String[] args ) throws InterruptedException {
        Thread sendingThread = new Thread( createSendingThread() );
        Thread listeningThread = new Thread( createListeningThread() );
        
        sendingThread.setDaemon( true );
        listeningThread.setDaemon( true );
        
        listeningThread.start();
        sendingThread.start();
        
        Thread.sleep( 26000 );
    }

    private static Runnable createSendingThread () {
        return new Runnable() {
            private int counter = 20;

            public void run () {

                while ( counter > 0 ) {
                    Metric metric = createMetric();
                    Gson gson = new Gson();
                    String json = gson.toJson( metric );
                    try {
                        sendMessage( json );
                    } catch ( IOException ex ) {
                        System.out.println( ex );
                    }

                    counter--;
                    try {
                        Thread.sleep( 5000 );
                    } catch ( InterruptedException ex ) {
                        System.out.println( ex );
                    }
                }
            }

            private Metric createMetric () {
                Metric metric = new MetricImpl();
                metric.setId( 11 );
                return metric;
            }
            private final static String QUEUE_NAME = "cpu_request";

            private void sendMessage ( String message )
                    throws IOException {
                ConnectionFactory factory = new ConnectionFactory();
                factory.setHost( Configuration.RABBIT_MQ_HOST );
                Connection connection = factory.newConnection();
                Channel channel = connection.createChannel();

                channel.queueDeclare( QUEUE_NAME, false, false, false, null );
                channel.basicPublish( "", QUEUE_NAME, null, message.getBytes() );
                System.out.println( " [x][Master] Sent '" + message + "'" );

                channel.close();
                connection.close();
            }
        };
    }

    private static Runnable createListeningThread () {
        return new Runnable() {
            private final static String QUEUE_NAME = "cpu_ansver";

            public void run () {
                try {
                    ConnectionFactory factory = new ConnectionFactory();
                    factory.setHost( Configuration.RABBIT_MQ_HOST );
                    
                    Connection connection = factory.newConnection();
                    Channel channel = connection.createChannel();

                    channel.queueDeclare( QUEUE_NAME, false, false, false, null );

                    QueueingConsumer consumer = new QueueingConsumer( channel );
                    channel.basicConsume( QUEUE_NAME, true, consumer );

                    while ( true ) {
                        QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                        String message = new String( delivery.getBody() );
                        System.out.println( " [x][Master] Received '" + message + "'" );
                    }
                } catch ( Exception ex ) {
                    System.out.println( ex );
                }
            }
        };
    }
}