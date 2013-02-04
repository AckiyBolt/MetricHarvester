package harvester.core;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author T@urus
 */
public class Configuration {
    
    public static String RABBIT_MQ_HOST = "rabbitmq";
    
    public static final Map<String, Integer> metrics;
    static {
        metrics = new HashMap<String, Integer>();
        metrics.put("FQDN", 10);
        metrics.put("CPUPercent", 11);
    }
}
