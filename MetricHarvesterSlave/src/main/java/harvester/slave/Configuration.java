package harvester.slave;

import harvester.slave.metricprov.AbstractSigarGateway;
import harvester.slave.metricprov.gateway.FQDNGateway;
import harvester.slave.metricprov.gateway.CPUGateway;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author T@urus
 */
public class Configuration {

    public static final Map<Integer, AbstractSigarGateway> FILLERS_MAP =
            new HashMap<Integer, AbstractSigarGateway>();

    static {
        FILLERS_MAP.put( 10, new FQDNGateway() );
        FILLERS_MAP.put( 11, new CPUGateway() );
    }
    
    public static final String SIGAR_EXCEPTION_VALUE = "SigarException have a place";
}