package harvester.core.conversation;

import harvester.core.message.Message;
import harvester.model.entity.Metric;

/**
 *
 * @author T@urus
 */
public interface Conversation {

    void sendMessage ( Message message, String resourceName );

    Message reciveMessage ( String resourceName );
}