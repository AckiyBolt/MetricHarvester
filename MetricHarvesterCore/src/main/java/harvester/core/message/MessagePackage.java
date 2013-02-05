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
        
        Date curDate = getCurrentDate();
        
        for ( Message message : getMessages() )
            message.setSendRequest( curDate );
    }
    
    public void makeReciveRequestDates () {
        
        Date curDate = getCurrentDate();
        
        for ( Message message : getMessages() )
            message.setReciveRequest( curDate );
    }

    public void makeSendResponseDates () {
        
        Date curDate = getCurrentDate();
        
        for ( Message message : getMessages() )
            message.setSendResponse( curDate );
    }
    
    public void makeReciveResponseDates () {
        
        Date curDate = getCurrentDate();
        
        for ( Message message : getMessages() )
            message.setReciveResponse( curDate );
    }

    private Date getCurrentDate () {
        return Calendar.getInstance().getTime();
    }
}
