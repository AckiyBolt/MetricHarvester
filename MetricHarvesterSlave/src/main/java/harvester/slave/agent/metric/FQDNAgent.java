package harvester.slave.agent.metric;

import harvester.core.message.Message;
import harvester.core.message.SynchronizedMessageBuffer;
import harvester.slave.agent.SigarAgent;
import org.hyperic.sigar.SigarException;

public class FQDNAgent
        extends SigarAgent {

    public FQDNAgent ( SynchronizedMessageBuffer messageBuffer ) {
        super( "FQDN", messageBuffer );
    }

    @Override
    protected void loadValue ( Message message )
            throws SigarException {
        
        String result = sigar.getFQDN();
        message.setResult( result );
    }

}