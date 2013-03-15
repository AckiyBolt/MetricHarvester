package harvester.core.agent.conversation;

import static harvester.core.agent.AgentState.*;
import harvester.core.message.Message;
import harvester.core.message.buffer.SynchronizedMessageBuffer;
import java.util.Calendar;

/**
 *
 * @author Kostiantyn_Belentsov
 */
public class MessageReciveAgent
        extends ConversationAgent {

    public MessageReciveAgent ( SynchronizedMessageBuffer messageBuffer, String queueName ) {
        super( messageBuffer, "MessageReciveAgent_", queueName );
        this.state = ACTIVE;
    }

    @Override
    protected void makeJob () {

        Message message = conversation.reciveMessage( queueName );
        
        message.setReciveRequest( Calendar.getInstance().getTime() );
        buffer.putInput( message );
    }

    @Override
    protected boolean stateIsChangable () {
        return false;
    }
}