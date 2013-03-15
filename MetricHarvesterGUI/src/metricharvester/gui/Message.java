package metricharvester.gui;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import java.io.Serializable;
import java.util.Date;
import org.bson.types.ObjectId;

/**
 *
 * @author T@urus
 */

@Entity
public class Message
        implements Serializable {

    private static final long versionId = 1L;
    
    @Id
    private ObjectId id;
    private Date sendRequest;
    private Date reciveRequest;
    private Date sendResponse;
    private Date reciveResponse;
    private String command;
    private String result;
    private String slaveId;

    public Message () {
    }

    public Date getSendRequest () {
        return sendRequest;
    }

    public void setSendRequest ( Date sendRequest ) {
        this.sendRequest = sendRequest;
    }

    public Date getReciveRequest () {
        return reciveRequest;
    }

    public void setReciveRequest ( Date reciveRequrst ) {
        this.reciveRequest = reciveRequrst;
    }

    public Date getSendResponse () {
        return sendResponse;
    }

    public void setSendResponse ( Date sendRespose ) {
        this.sendResponse = sendRespose;
    }

    public Date getReciveResponse () {
        return reciveResponse;
    }

    public void setReciveResponse ( Date reciveResponse ) {
        this.reciveResponse = reciveResponse;
    }

    public String getCommand () {
        return command;
    }

    public void setCommand ( String command ) {
        this.command = command;
    }

    public String getResult () {
        return result;
    }

    public void setResult ( String result ) {
        this.result = result;
    }

    public String getSlaveId () {
        return slaveId;
    }

    public void setSlaveId ( String slaveId ) {
        this.slaveId = slaveId;
    }

    @Override
    public final int hashCode () {
        int hash = 5;
        hash = 97 * hash +
               ( this.sendRequest != null
                ? this.sendRequest.hashCode()
                : 0 );
        hash = 97 * hash + ( this.command != null
                ? this.command.hashCode()
                : 0 );
        hash = 97 * hash + ( this.result != null
                ? this.result.hashCode()
                : 0 );
        hash = 97 * hash + ( this.slaveId != null
                ? this.slaveId.hashCode()
                : 0 );
        return hash;
    }

    @Override
    public final boolean equals ( Object obj ) {
        if ( obj == null )
            return false;
        if ( !(obj instanceof Message) )
            return false;
        final Message other = ( Message ) obj;
        if ( this.sendRequest != other.sendRequest &&
             ( this.sendRequest == null ||
               !this.sendRequest.equals( other.sendRequest ) ) )
            return false;
        if ( ( this.command == null )
                ? ( other.command != null )
                : !this.command.equals( other.command ) )
            return false;
        if ( ( this.result == null )
                ? ( other.result != null )
                : !this.result.equals( other.result ) )
            return false;
        if ( ( this.slaveId == null )
                ? ( other.slaveId != null )
                : !this.slaveId.equals( other.slaveId ) )
            return false;
        return true;
    }

    @Override
    public String toString () {
        return "Message {" +
                "\n   sendRequest=    " + sendRequest +
               ",\n   reciveRequest=  " + reciveRequest +
               ",\n   sendResponse=   " + sendResponse +
               ",\n   reciveResponse= " + reciveResponse +
               ",\n   command=        " + command +
               ",\n   result=         " + result +
               ",\n   slaveId=        " + slaveId +
               "\n}";
    }
}