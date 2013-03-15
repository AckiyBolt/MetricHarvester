package harvester.model.entity;

import java.io.Serializable;
import java.util.Date;
import com.google.code.morphia.annotations.Entity;
import org.bson.types.ObjectId;
import com.google.code.morphia.annotations.Id;

/**
 *
 * @author T@urus
 */

@Entity
public class Metric
        implements Serializable {
    
    @Id
    private ObjectId id;
    private Date sendRequest;
    private Date reciveRequest;
    private Date sendResponse;
    private Date reciveResponse;
    private String metric;
    private String result;
    private String clientName;

    public Metric () {
    }

    public ObjectId getId () {
        return id;
    }

    public void setId ( ObjectId id ) {
        this.id = id;
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

    public String getMetric () {
        return metric;
    }

    public void setMetric ( String command ) {
        this.metric = command;
    }

    public String getResult () {
        return result;
    }

    public void setResult ( String result ) {
        this.result = result;
    }

    public String getClientName () {
        return clientName;
    }

    public void setClientName ( String clientName ) {
        this.clientName = clientName;
    }

    @Override
    public final int hashCode () {
        int hash = 5;
        hash = 97 * hash +
               ( this.sendRequest != null
                ? this.sendRequest.hashCode()
                : 0 );
        hash = 97 * hash + ( this.metric != null
                ? this.metric.hashCode()
                : 0 );
        hash = 97 * hash + ( this.result != null
                ? this.result.hashCode()
                : 0 );
        hash = 97 * hash + ( this.clientName != null
                ? this.clientName.hashCode()
                : 0 );
        return hash;
    }

    @Override
    public final boolean equals ( Object obj ) {
        if ( obj == null )
            return false;
        if ( !(obj instanceof Metric) )
            return false;
        final Metric other = ( Metric ) obj;
        if ( this.sendRequest != other.sendRequest &&
             ( this.sendRequest == null ||
               !this.sendRequest.equals( other.sendRequest ) ) )
            return false;
        if ( ( this.metric == null )
                ? ( other.metric != null )
                : !this.metric.equals( other.metric ) )
            return false;
        if ( ( this.result == null )
                ? ( other.result != null )
                : !this.result.equals( other.result ) )
            return false;
        if ( ( this.clientName == null )
                ? ( other.clientName != null )
                : !this.clientName.equals( other.clientName ) )
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
               ",\n   command=        " + metric +
               ",\n   result=         " + result +
               ",\n   clientName=     " + clientName +
               "\n}";
    }
}