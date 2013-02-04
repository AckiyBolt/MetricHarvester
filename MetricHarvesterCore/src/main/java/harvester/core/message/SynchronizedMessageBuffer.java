package harvester.core.message;

import java.util.Collection;

/**
 *
 * @author Kostiantyn_Belentso
 */
public class SynchronizedMessageBuffer
        extends MessageBuffer {

    @Override
    public synchronized Collection<Message> getMessages () {
        return super.getMessages();
    }

    @Override
    public synchronized void put ( Message message ) {
        super.put( message );
    }

    @Override
    public synchronized void putAll ( Collection<Message> collection ) {
        super.putAll( collection );
    }

    @Override
    public synchronized Message push ( String command ) {
        return super.push( command );
    }
}
