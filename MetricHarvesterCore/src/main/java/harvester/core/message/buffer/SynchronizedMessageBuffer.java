package harvester.core.message.buffer;

import harvester.core.message.Message;
import harvester.model.entity.Metric;
import java.util.Collection;

/**
 *
 * @author Kostiantyn_Belentso
 */
public class SynchronizedMessageBuffer
        extends MessageBuffer {

    @Override
    public synchronized Collection<Message> pushMetrics () {
        return super.pushMetrics();
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
    public synchronized Message push ( String message ) {
        Message result = super.push( message );
        return result;
    }
}
