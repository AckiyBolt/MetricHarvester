package harvester.core.agent.conversation;

import harvester.core.message.Message;
import harvester.core.message.buffer.SynchronizedMessageBuffer;
import java.util.Calendar;

/**
 *
 * @author Kostiantyn_Belentsov
 */
public class MessageSendAgent
        extends ConversationAgent {

    public MessageSendAgent ( SynchronizedMessageBuffer messageBuffer, String queueName ) {
        super( messageBuffer, "MessageSendAgent_", queueName );
    }

    @Override
    protected void makeJob () {

        Message message = buffer.push( "toBeSend" );
        
        if ( message == null )
            return;
        
        message.setSendResponse( Calendar.getInstance().getTime() );
        conversation.sendMessage( message, queueName );
    }

    @Override
    protected boolean stateIsChangable () {
        return true;
    }
}