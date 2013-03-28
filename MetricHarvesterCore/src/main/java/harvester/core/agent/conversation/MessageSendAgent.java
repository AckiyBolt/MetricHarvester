package harvester.core.agent.conversation;

import harvester.core.message.Message;
import harvester.core.message.buffer.SynchronizedMessageBuffer;
import java.util.Calendar;

/**
 *
 * @author Kostiantyn_Belentsov
 */
public abstract class MessageSendAgent
        extends ConversationAgent {

    private static final String TO_BE_SEND_MARK = "toBeSend";

    public MessageSendAgent ( SynchronizedMessageBuffer messageBuffer, String queueName ) {
        super( messageBuffer, "MessageSendAgent_", queueName );
    }

    @Override
    protected void makeJob () {

        Message message = buffer.push( TO_BE_SEND_MARK );
        if ( message == null )
            return;

        fillMessage( message );
        conversation.sendMessage( message, queueName );
    }

    @Override
    protected boolean stateIsChangable () {
        return true;
    }

    protected abstract void fillMessage ( Message message );

    public static class Request
            extends MessageSendAgent {

        public Request ( SynchronizedMessageBuffer messageBuffer, String queueName ) {
            super( messageBuffer, queueName );
        }

        @Override
        protected void fillMessage ( Message message ) {
            message.setSendRequest( Calendar.getInstance().getTime() );
        }
    }

    public static class Response
            extends MessageSendAgent {

        private String senderName;

        public Response ( SynchronizedMessageBuffer messageBuffer, String queueName, String senderName ) {
            super( messageBuffer, queueName );
            this.senderName = senderName;
        }

        @Override
        protected void fillMessage ( Message message ) {
            message.setSendResponse( Calendar.getInstance().getTime() );
            message.setSenderName( senderName );
        }
    }
}