package harvester.core.conversation;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;
import harvester.core.JSONConverter;
import harvester.core.message.Message;
import harvester.model.entity.Metric;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author T@urus
 */
public class SimpleConversation
        implements Conversation {

    private ConnectionProvider provider;
    private JSONConverter<Message> converter;

    public SimpleConversation () {
        provider = new ConnectionProvider();
        converter = new JSONConverter<Message>();
    }

    public void sendMessage ( Message message, String resourceName ) {
        Channel channel = null;
        String msg = converter.toString( message );

        try {
            channel = provider.createChannel( resourceName );
            channel.basicPublish( "", resourceName, null, msg.getBytes() );
            provider.closeChannel( channel );

        } catch ( IOException ex ) {
            Logger.getLogger( ConversationProvider.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }

    public Message reciveMessage ( String resourceName ) {

        Message result = null;
        Channel channel = null;

        try {
            channel = provider.createChannel( resourceName );
            QueueingConsumer consumer = new QueueingConsumer( channel );
            channel.basicConsume( resourceName, true, consumer );

            QueueingConsumer.Delivery delivery = null;

            try {
                delivery = consumer.nextDelivery();

            } catch ( InterruptedException ex ) {
                Logger.getLogger( this.getClass().getName() ).log( Level.SEVERE, null, ex );

            } catch ( ShutdownSignalException ex ) {
                Logger.getLogger( this.getClass().getName() ).log( Level.SEVERE, null, ex );

            } catch ( ConsumerCancelledException ex ) {
                Logger.getLogger( this.getClass().getName() ).log( Level.SEVERE, null, ex );
            }

            String message = new String( delivery.getBody() );

            result = converter.toObject( message, Message.class );

            provider.closeChannel( channel );

        } catch ( IOException ex ) {
            Logger.getLogger( this.getClass().getName() ).log( Level.SEVERE, null, ex );
        }

        return result;
    }
}