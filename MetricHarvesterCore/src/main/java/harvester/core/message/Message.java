package harvester.core.message;

import java.util.Date;

/**
 *
 * @author T@urus
 */
public class Message {
    
    private String request;
    private String response;
    private Date sendRequest;
    private Date reciveRequest;
    private Date sendResponse;
    private Date reciveResponse;

    public Message () {
    }

    public String getRequest () {
        return request;
    }

    public void setRequest ( String request ) {
        this.request = request;
    }

    public String getResponse () {
        return response;
    }

    public void setResponse ( String response ) {
        this.response = response;
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

    public void setReciveRequest ( Date reciveRequest ) {
        this.reciveRequest = reciveRequest;
    }

    public Date getSendResponse () {
        return sendResponse;
    }

    public void setSendResponse ( Date sendResponse ) {
        this.sendResponse = sendResponse;
    }

    public Date getReciveResponse () {
        return reciveResponse;
    }

    public void setReciveResponse ( Date reciveResponse ) {
        this.reciveResponse = reciveResponse;
    }

    @Override
    public int hashCode () {
        int hash = 3;
        hash = 13 * hash + ( this.request != null ? this.request.hashCode() : 0 );
        hash = 13 * hash + ( this.response != null ? this.response.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals ( Object obj ) {
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        final Message other = ( Message ) obj;
        if ( ( this.request == null ) ? ( other.request != null ) : !this.request.equals( other.request ) )
            return false;
        if ( ( this.response == null ) ? ( other.response != null ) : !this.response.equals( other.response ) )
            return false;
        return true;
    }

    @Override
    public String toString () {
        return "Message{" + "request=" + request + ", response=" + response + "}";
    }
}