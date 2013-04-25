package harvester.configuration;

/**
 *
 * @author T@urus
 */
public class Configuration {

//    public static final class DB {
//
//        public static final String HOST = getValue( "host" );
//        public static final String PORT = getValue( "port" );
//        public static final String DATA_BASE = getValue( "db" );
//    }
//
//    public static final class RABBIT_MQ {
//
//        public static final String HOST = getValue( "host" );
//        public static final String CLIENT_REGISTRATION_QUEUE = getValue( "client_registration_queue" );
//        public static final String CLIENT_RESPONSE_QUEUE = getValue( "client_response_queue" );
//    }
    
    

    public static final class DB {

        public static final String HOST = "mongo";
        public static final String PORT = "27017";
        public static final String DATA_BASE = "testMetrics";
    }

    public static final class RABBIT_MQ {

        public static final String HOST = "rabbitmq";
        public static final String CLIENT_REGISTRATION_QUEUE = "client_reg";
        public static final String CLIENT_RESPONSE_QUEUE = "client_answer";
    }

    private static String getValue ( String key ) {
        return PropertyLookuper.DB.getProperty( key );
    }
}