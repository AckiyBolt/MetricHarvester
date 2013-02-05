package harvester.core.agent;

import harvester.core.message.Message;
import harvester.core.message.SynchronizedMessageBuffer;

/**
 *
 * @author Kostiantyn_Belentso
 */
public abstract class MessageTaskAgent extends TaskAgent<SynchronizedMessageBuffer, Message> {

    private SynchronizedMessageBuffer buffer;
    
    public MessageTaskAgent ( String name, SynchronizedMessageBuffer monitor ) {
        super( name, monitor );
        this.buffer = monitor;
    }

    @Override
    public abstract void makeJob ( Message message );

    @Override
    public void makeJob () {
        Message message = buffer.push( this.getName() );
        makeJob( message );
        buffer.put( message );
    }

}
