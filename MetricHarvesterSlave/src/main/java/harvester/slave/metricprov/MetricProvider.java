package harvester.slave.metricprov;

import harvester.slave.Configuration;
import java.util.*;

public class MetricProvider {

    private Map<Integer, AbstractSigarGateway> fillers;

    public MetricProvider () {
        initFillers();
    }

    private void initFillers () {
        fillers = Configuration.FILLERS_MAP;
    }

    public void fillMetric ( Metric metric ) {
        fillers.get( metric.getId() ).fill( metric );
    }
}