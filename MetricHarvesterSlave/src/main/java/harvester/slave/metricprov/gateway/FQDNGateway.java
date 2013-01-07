package harvester.slave.metricprov.gateway;

import harvester.slave.metricprov.AbstractSigarGateway;
import org.hyperic.sigar.SigarException;

public class FQDNGateway
        extends AbstractSigarGateway {

    @Override
    protected String loadValue ()
            throws SigarException {
        
        return sigar.getFQDN();
    }
}