package harvester.slave.agent;

import static harvester.core.agent.AgentState.*;
import harvester.core.agent.MessageTaskAgent;
import harvester.core.message.Message;
import harvester.core.message.SynchronizedMessageBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public abstract class SigarAgent
        extends MessageTaskAgent {

    protected Sigar sigar = new Sigar();

    public SigarAgent ( String name, SynchronizedMessageBuffer messageBuffer ) {
        super( name, messageBuffer );
    }

    @Override
    protected void makeJob ( Message message ) {
        try {
            loadValue( message );

        } catch ( SigarException ex ) {
            Logger.getLogger( SigarAgent.class.getName() ).log( Level.SEVERE, null, ex );
            state = DEAD;
        }
    }

    protected abstract void loadValue ( Message message )
            throws SigarException;

    @Override
    protected boolean stateIsChangable () {
        return true;
    }
}