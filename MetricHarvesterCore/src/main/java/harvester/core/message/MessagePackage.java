package harvester.core.message;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author T@urus
 */
public class MessagePackage
        implements Serializable {

    private Collection<Message> messages;

    public Collection<Message> getMessages () {
        if ( messages == null )
            messages = new LinkedList<Message>();

        return messages;
    }

    public void setMessages ( Collection<Message> messages ) {
        this.messages = messages;
    }

    public void addMessage ( Message message ) {
        
        if (message == null)
            return;
        
        getMessages().add( message );
    }

    public void makeSendRequestDates () {
        for ( Message message : getMessages() )
            message.setSendRequest( getCurrentDate() );
    }
    
    public void makeReciveRequestDates () {
        for ( Message message : getMessages() )
            message.setReciveRequest( getCurrentDate() );
    }

    public void makeSendResponseDates () {
        for ( Message message : getMessages() )
            message.setSendResponse( getCurrentDate() );
    }
    
    public void makeReciveResponseDates () {
        for ( Message message : getMessages() )
            message.setReciveResponse( getCurrentDate() );
    }

    private Date getCurrentDate () {
        return Calendar.getInstance().getTime();
    }
}
