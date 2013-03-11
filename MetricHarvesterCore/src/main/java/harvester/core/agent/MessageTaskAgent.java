package harvester.core.agent;

import harvester.core.message.Message;
import harvester.core.message.SynchronizedMessageBuffer;

/**
 *
 * @author Kostiantyn_Belentso
 */
public abstract class MessageTaskAgent
        extends TaskAgent<SynchronizedMessageBuffer, Message> {

    private final SynchronizedMessageBuffer buffer;

    public MessageTaskAgent ( String name, SynchronizedMessageBuffer monitor ) {
        super( name, monitor );
        this.buffer = monitor;
    }

    @Override
    protected abstract void makeJob ( Message message );

    @Override
    protected void makeJob () {
        Message message = buffer.push( this.getName() );
        
        if ( message == null )
            return;

        makeJob( message );
        buffer.putOutput( message );
    }
}