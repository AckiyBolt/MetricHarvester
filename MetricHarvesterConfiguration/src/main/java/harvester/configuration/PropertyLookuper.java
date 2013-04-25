package harvester.configuration;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 *
 * @author T@urus
 */
public class PropertyLookuper {

    private static final String DB_NAME = "db.properties";
    private static final String RABBIT_NAME = "rabbit.properties";

    public static class DB {

        private static Properties prop = lookup( DB_NAME );
        
        public static String getProperty (String key) {
            return prop.getProperty( key );
        }
    }

    public static class RABBIT {

        private static Properties prop = lookup( RABBIT_NAME );
        
        public static String getProperty (String key) {
            return prop.getProperty( key );
        }
    }

    private static Properties lookup ( String propName ) {
        Properties prop = new Properties();
        try {
            URL url = ClassLoader.getSystemResource(propName);
            prop.load(url.openStream());
            
        } catch ( IOException ex ) {
            ex.printStackTrace();
        }
        return prop;
    }
}