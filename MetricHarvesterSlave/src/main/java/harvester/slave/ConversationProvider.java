package harvester.slave;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;
import harvester.slave.metricprov.Metric;
import harvester.slave.metricprov.MetricImpl;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConversationProvider {

    private ConnectionProvider provider;
    private final String RECIVE_QUEUE_NAME = "cpu_request";
    private final String SEND_QUEUE_NAME = "cpu_ansver";
    private Gson gson;

    public ConversationProvider () {
        provider = new ConnectionProvider();
        gson = new Gson();
    }

    public boolean registrate ( Metric ip ) {
        // TODO: implement
        return false;
    }

    public Metric reciveMetric () {

        Metric result = null;
        Channel channel = null;

        try {
            channel = provider.createChannel( RECIVE_QUEUE_NAME );
            QueueingConsumer consumer = new QueueingConsumer( channel );
            channel.basicConsume( RECIVE_QUEUE_NAME, true, consumer );

            QueueingConsumer.Delivery delivery = null;

            try {
                delivery = consumer.nextDelivery();

            } catch ( InterruptedException ex ) {
                Logger.getLogger( ConversationProvider.class.getName() ).log( Level.SEVERE, null, ex );

            } catch ( ShutdownSignalException ex ) {
                Logger.getLogger( ConversationProvider.class.getName() ).log( Level.SEVERE, null, ex );

            } catch ( ConsumerCancelledException ex ) {
                Logger.getLogger( ConversationProvider.class.getName() ).log( Level.SEVERE, null, ex );
            }

            String message = new String( delivery.getBody() );
            
            result = gson.fromJson( message, MetricImpl.class );

            provider.closeChannel( channel );
            System.out.println( " [x][Slave] Received '" + message + "'" );

        } catch ( IOException ex ) {
            Logger.getLogger( ConversationProvider.class.getName() ).log( Level.SEVERE, null, ex );
        }

        return result;
    }

    public void sendMetric ( Metric metric ) {

        Channel channel = null;
        String message = gson.toJson( metric );

        try {
            channel = provider.createChannel( SEND_QUEUE_NAME );
            channel.basicPublish( "", SEND_QUEUE_NAME, null, message.getBytes() );
            provider.closeChannel( channel );
            
            System.out.println( " [x][Slave] Sent '" + message + "'" );
            
        } catch ( IOException ex ) {
            Logger.getLogger( ConversationProvider.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }

    private class ConnectionProvider {

        private ConnectionFactory factory;

        ConnectionProvider () {
            factory = new ConnectionFactory();
            factory.setHost( Configuration.RABBIT_MQ_HOST );
        }

        private Channel createChannel ( String queueName )
                throws IOException {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare( queueName, false, false, false, null );
            return channel;
        }

        private void closeChannel ( Channel channel )
                throws IOException {
            channel.close();
            channel.getConnection().close();
        }
    }
}