package harvester.slave.agent;

import harvester.core.agent.Agent;
import harvester.core.message.Message;
import harvester.core.message.SynchronizedMessageBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public abstract class SigarAgent
        extends Agent {

    protected Sigar sigar = new Sigar();
    protected SynchronizedMessageBuffer messageBuffer;

    public SigarAgent ( String name, SynchronizedMessageBuffer messageBuffer ) {
        super( name );
        this.messageBuffer = messageBuffer;
    }

    @Override
    public void run () {

        while ( true ) {

            Message message = messageBuffer.push( this.getName() );
            
            try {
                loadValue( message );
                
            } catch ( SigarException ex ) {
                Logger.getLogger( SigarAgent.class.getName() ).log( Level.SEVERE, null, ex );
                break;
            }
            
            messageBuffer.put( message );

            try {
                messageBuffer.wait();
            } catch ( InterruptedException ex ) {
                break;
            }
        }
    }

    protected abstract void loadValue ( Message message )
            throws SigarException;
}