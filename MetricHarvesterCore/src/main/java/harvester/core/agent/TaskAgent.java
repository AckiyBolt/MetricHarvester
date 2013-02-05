package harvester.core.agent;

import static harvester.core.agent.AgentState.*;
import harvester.core.message.Message;
import harvester.core.message.SynchronizedMessageBuffer;

/**
 *
 * @author Kostiantyn_Belentso
 */
public abstract class TaskAgent <M, T>
        extends ActionAgent {

    public TaskAgent (String name, M monitor ) {
        super( name, monitor );
    }
    
    public abstract void makeJob (T task);
}