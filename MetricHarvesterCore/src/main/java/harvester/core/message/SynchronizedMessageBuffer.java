package harvester.core.message;

import java.util.Collection;

/**
 *
 * @author Kostiantyn_Belentso
 */
public class SynchronizedMessageBuffer
        extends MessageBuffer {

    @Override
    public synchronized Collection<Message> pushMessages () {
        return super.pushMessages();
    }

    @Override
    public synchronized void putInput ( Message message ) {
        super.putInput( message );
        this.notifyAll();
    }

    @Override
    public synchronized void putOutput ( Message message ) {
        super.putOutput( message );
        this.notifyAll();
    }

    @Override
    public synchronized Message push ( String command ) {
        Message message = super.push( command );
        return message;
    }
}
