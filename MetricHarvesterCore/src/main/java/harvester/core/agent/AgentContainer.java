package harvester.core.agent;

import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author T@urus
 */
public class AgentContainer {

    private Collection<Agent> agents;

    public void startAgents () {
        for ( Agent agent : getAgents() ) {
            agent.start();
        }
    } 
    
    public void startAgents (String ... names) {
        for ( Agent agent : getAgents() )
            for ( String name : names )
                if (agent.getName().equals( name ) )
                    agent.start();
    } 
    
    public Collection<Agent> getAgents () {
        if ( agents == null )
            agents = new LinkedList<Agent>();
        return agents;
    }

    public void setAgents ( Collection<Agent> agents ) {
        this.agents = agents;
    }
}