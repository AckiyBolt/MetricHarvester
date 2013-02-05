package harvester.core.message;

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

    protected Collection<Message> getMessages () {

        Collection<Message> result = null;

        if ( messages != null )
            result = messages.values();

        if ( result == null )
            result = Collections.EMPTY_LIST;

        return result;
    }

    public void put ( Message message ) {

        if ( message == null || message.getCommand() == null
             || message.getCommand().isEmpty() )
            return;

        if ( messages == null )
            messages = new HashMap<String, Message>();

        messages.put( message.getCommand(), message );
    }

    public void putAll ( Collection<Message> collection ) {

        if ( collection != null )
            for ( Message message : collection )
                put( message );
    }

    public void putPackage ( MessagePackage pack ) {
        if ( pack != null )
            putAll( pack.getMessages() );
    }

    public Message push ( String command ) {

        Message result = null;

        if ( messages != null && !messages.isEmpty() )
            result = messages.remove( command );

        return result;
    }

    public Collection<Message> pushMessages () {

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