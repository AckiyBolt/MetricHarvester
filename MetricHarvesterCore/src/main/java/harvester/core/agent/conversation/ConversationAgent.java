package harvester.core.agent.conversation;

import harvester.core.agent.ActionAgent;
import static harvester.core.agent.AgentState.*;
import harvester.core.conversation.Conversation;
import harvester.core.conversation.ConversationProvider;
import harvester.core.message.Message;
import harvester.core.message.buffer.SynchronizedMessageBuffer;
import java.util.Calendar;

/**
 *
 * @author Kostiantyn_Belentsov
 */
public abstract class ConversationAgent
        extends ActionAgent {

    protected Conversation conversation;
    protected String queueName;
    protected final SynchronizedMessageBuffer buffer;

    public ConversationAgent ( SynchronizedMessageBuffer messageBuffer, String name, String queueName ) {
        super( name + queueName, messageBuffer );
        
        this.queueName = queueName;
        this.buffer = messageBuffer;
        this.conversation = ConversationProvider.createSimpleConversation();
    }
}