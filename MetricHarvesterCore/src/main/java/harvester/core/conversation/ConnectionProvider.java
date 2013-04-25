package harvester.core.conversation;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import harvester.configuration.Configuration;
import java.io.IOException;

/**
 *
 * @author Kostiantyn_Belentso
 */
public class ConnectionProvider {

        private ConnectionFactory factory;

        ConnectionProvider () {
            factory = new ConnectionFactory();
            factory.setHost( Configuration.RABBIT_MQ.HOST );
        }

        public Channel createChannel ( String queueName )
                throws IOException {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare( queueName, false, false, false, null );
            return channel;
        }

        public void closeChannel ( Channel channel )
                throws IOException {
            channel.close();
            channel.getConnection().close();
        }
    }