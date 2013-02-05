package harvester.core.agent;

import static harvester.core.agent.AgentState.*;
import harvester.core.message.Message;
import harvester.core.message.SynchronizedMessageBuffer;

/**
 *
 * @author T@urus
 */
public abstract class Agent
        extends Thread {

    protected AgentState state;

    public Agent ( String name ) {

        super( name );

        this.setDaemon( true );
        state = IDLE;
    }

    @Override
    public abstract void run ();
}