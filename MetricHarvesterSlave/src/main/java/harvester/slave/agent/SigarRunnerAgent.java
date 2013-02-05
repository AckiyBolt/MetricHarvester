package harvester.slave.agent;

import harvester.core.agent.ActionAgent;
import harvester.core.agent.Agent;
import harvester.core.agent.AgentContainer;
import harvester.core.message.Message;
import harvester.core.message.SynchronizedMessageBuffer;

/**
 *
 * @author Kostiantyn_Belentso
 */
public class SigarRunnerAgent
        extends ActionAgent {

    private AgentContainer container;

    public SigarRunnerAgent ( SynchronizedMessageBuffer messageBuffer, AgentContainer container ) {
        super( "SigarRunner", messageBuffer );
        this.container = container;
    }

    @Override
    public void makeJob ( ) {
        
        while (true) {
            
        }
        
    }
}