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
public class MessageSendAgent
        extends ActionAgent {

    private Conversation conversation;
    private final SynchronizedMessageBuffer buffer;

    public MessageSendAgent ( SynchronizedMessageBuffer messageBuffer ) {
        super( "MessageSendAgent", messageBuffer );
        buffer = messageBuffer;
        conversation = ConversationProvider.createSimpleConversation();
    }

    @Override
    protected void makeJob () {

        Message message = buffer.push( "toBeSend" );
        
        if ( message == null )
            return;
        
        message.setSendResponse( Calendar.getInstance().getTime() );
        conversation.sendMessage( message, "responseQueue" );
    }

    @Override
    protected boolean stateIsChangable () {
        return true;
    }
}