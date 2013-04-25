package harvester.model.db;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;
import harvester.configuration.Configuration;
import harvester.model.entity.*;
import java.io.Serializable;
import java.net.UnknownHostException;

/**
 *
 * @author T@urus
 */
public class DatastoreManager
        implements Serializable {

    private Datastore datastore;

    public Datastore getDatastore () {
        if ( datastore == null )
            createDatastore();
        return datastore;
    }

    private void createDatastore () {

        String host = Configuration.DB.HOST;
        int port = Integer.valueOf( Configuration.DB.PORT );
        String db = Configuration.DB.DATA_BASE;

        Mongo mongo = null;
        Morphia morphia = null;
        Datastore datastore = null;

        try {
            mongo = new Mongo( host, port );
            morphia = new Morphia();
            makeMapping( morphia );
            datastore = morphia.createDatastore( mongo, db );
            System.out.println( "Using MongoDB at : " + host + ":" + port + "\\" + db);

        } catch ( UnknownHostException ex ) {
            System.out.println("*** Error. Can't find mongo by host: " + host + ":" + port);
        }
        
        this.datastore = datastore;
    }

    private void makeMapping ( Morphia morphia ) {
        morphia.map( Client.class );
        morphia.map( Metric.class );
        morphia.map( Task.class );
    }
}