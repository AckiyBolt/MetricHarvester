package harvester.core.agent;

import static harvester.core.agent.AgentState.*;
import harvester.core.message.Message;
import harvester.core.message.SynchronizedMessageBuffer;

/**
 *
 * @author Kostiantyn_Belentso
 */
public abstract class ActionAgent
        extends Agent {
    
    protected Object monitor;
    
    public ActionAgent (String name, Object monitor ) {
        super( name );
        this.monitor = monitor;
    }

    @Override
    public void run () {
        while ( state != DEAD ) {
            
                makeJob();

            changeState();
        }
    }
    
    public abstract void makeJob ();

    private void changeState () {
        
        try {

            if ( state == ACTIVE ) {
                state = IDLE;
                monitor.wait();
            }

            state = ACTIVE;

        } catch ( InterruptedException ex ) {
            state = DEAD;
        }
    }

}