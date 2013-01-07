package harvester.slave.metricprov;

import harvester.slave.Configuration;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public abstract class AbstractSigarGateway {

    protected static Sigar sigar = new Sigar();

    protected abstract String loadValue ()
            throws SigarException;

    public void fill ( Metric metric ) {
        try {
            metric.setValue( loadValue() );
        
        } catch (SigarException ex) {
            metric.setValue( Configuration.SIGAR_EXCEPTION_VALUE );
        }
    }
}