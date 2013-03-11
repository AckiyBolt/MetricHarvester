package harvester.slave.agent;

import harvester.core.agent.ActionAgent;
import static harvester.core.agent.AgentState.*;
import harvester.core.conversation.Conversation;
import harvester.core.conversation.ConversationProvider;
import harvester.core.message.Message;
import harvester.core.message.SynchronizedMessageBuffer;
import java.util.Calendar;

/**
 *
 * @author Kostiantyn_Belentsov
 */
public class MessageReciveAgent
        extends ActionAgent {

    private Conversation conversation;
    private final SynchronizedMessageBuffer buffer;

    public MessageReciveAgent ( SynchronizedMessageBuffer messageBuffer ) {
        super( "MessageReciveAgent", messageBuffer );
        buffer = messageBuffer;
        conversation = ConversationProvider.createSimpleConversation();
        state = ACTIVE;
    }

    @Override
    protected void makeJob () {

        Message message = conversation.reciveMessage( "requestQueue" );
        
        message.setReciveRequest( Calendar.getInstance().getTime() );
        buffer.putInput( message );
    }

    @Override
    protected boolean stateIsChangable () {
        return false;
    }
}