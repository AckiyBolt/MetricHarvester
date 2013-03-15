package harvester.core.message.buffer;

import harvester.core.message.Message;
import harvester.model.entity.Metric;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author T@urus
 */
public class MessageBuffer {

    private Map<String, Message> messages;

    protected Collection<Message> getMetrics () {

        Collection<Message> result = null;

        if ( messages != null )
            result = messages.values();

        if ( result == null )
            result = Collections.EMPTY_LIST;

        return result;
    }

    public void putInput ( Message message ) {

        if ( message == null || message.getRequest() == null
             || message.getRequest().isEmpty() )
            return;

        if ( messages == null )
            messages = new HashMap<String, Message>();

        messages.put( message.getRequest(), message );
    }

    public void putOutput ( Message message ) {

        if ( message == null || message.getRequest() == null
             || message.getRequest().isEmpty() )
            return;

        if ( messages == null )
            messages = new HashMap<String, Message>();

        messages.put( "toBeSend", message );
    }

    public Message push ( String message ) {

        Message result = null;

        if ( messages != null && !messages.isEmpty() )
            result = messages.remove( message );

        return result;
    }

    public Collection<Message> pushMetrics () {

        Collection<Message> result = null;

        if ( messages != null ) {
            result = messages.values();
            messages.clear();
        }
        else
            result = Collections.EMPTY_LIST;

        
        return result;
    }
}