package metricharvester.gui;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.DatastoreImpl;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

import java.io.Serializable;
import java.net.UnknownHostException;
import javax.annotation.PostConstruct;

/**
 *
 * @author T@urus
 */
public class DatabaseManager
        implements Serializable {

    private Datastore datastore;

    public DatabaseManager () throws UnknownHostException {
        createDatastore ();
    }

    public Datastore getDatastore () {
        return datastore;
    }

    private void createDatastore ()
            throws UnknownHostException {

        String host = "db-host";
        int port = 27017;
        String db = "testMetrics";

        Mongo mongo = new Mongo( host, port );
        Morphia morphia = new Morphia();
        makeMapping( morphia );

        datastore = morphia.createDatastore( mongo, db );
    }

    private void makeMapping ( Morphia morphia ) {
        morphia.map( Message.class );
    }
}
