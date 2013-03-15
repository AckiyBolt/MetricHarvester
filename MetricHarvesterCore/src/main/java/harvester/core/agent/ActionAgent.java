package harvester.core.agent;

import static harvester.core.agent.AgentState.*;
import harvester.model.entity.Metric;
import harvester.core.message.buffer.SynchronizedMessageBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kostiantyn_Belentso
 */
public abstract class ActionAgent
        extends Agent {

    protected final Object monitor;

    public ActionAgent ( String name, Object monitor ) {
        super( name );
        this.monitor = monitor;
    }

    @Override
    public void run () {
        while ( state != DEAD ) {
            chackState();
            makeJob();

            if ( stateIsChangable() )
                state = IDLE;
        }
    }

    protected abstract boolean stateIsChangable ();

    protected abstract void makeJob ();

    private void chackState () {
        try {
            if ( state == IDLE )
                synchronized ( monitor ) {
                    monitor.wait();
                }
            state = ACTIVE;

        } catch ( InterruptedException ex ) {
            state = DEAD;
        }
    }
}