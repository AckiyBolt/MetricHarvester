package harvester.slave;

import harvester.slave.metricprov.Metric;
import harvester.slave.metricprov.MetricImpl;
import harvester.slave.metricprov.MetricProvider;
import org.hyperic.sigar.Cpu;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class SlaveMain {

    private static MetricProvider metricProvider = new MetricProvider();

    public static void main ( String[] args )
            throws SigarException {

        Metric metric = new MetricImpl();
        metric.setId( 10 );

        metricProvider.fillMetric( metric );

        System.out.println( metric.getValue() );
    }
}