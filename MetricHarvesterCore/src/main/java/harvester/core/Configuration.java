package harvester.core;

/**
 *
 * @author T@urus
 */
public class Configuration {
    
    public static final class RABBIT_MQ {
        public static String HOST =                       "rabbitmq";
        public static String CLIENT_REGISTRATION_QUEUE =  "client_reg";
        public static String CLIENT_RESPONSE_QUEUE =      "client_answer";
    }
}