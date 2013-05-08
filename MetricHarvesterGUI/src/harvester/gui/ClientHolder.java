package harvester.gui;

import harvester.model.entity.Client;

/**
 *
 * @author T@urus
 */
public enum ClientHolder {

    INSTANCE;
    
    private Client client;

    public synchronized Client getClient () {
        
        if (client == null)
            client = new Client();
        return client;
    }

    public synchronized void setClient ( Client client ) {
        this.client = client;
    }
}
