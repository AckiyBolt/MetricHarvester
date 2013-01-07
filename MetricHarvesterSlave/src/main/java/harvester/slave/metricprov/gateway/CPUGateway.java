package harvester.slave.metricprov.gateway;

import harvester.slave.metricprov.AbstractSigarGateway;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class CPUGateway
        extends AbstractSigarGateway {

    @Override
    protected String loadValue ()
            throws SigarException {

        CpuPerc[] source = sigar.getCpuPercList();
        double buffer = 0.0;

        for ( int i = 0; i < source.length; i++ )
            buffer += 1.0 - source[i].getIdle();
        
        return String.valueOf( buffer / source.length );
    }
}