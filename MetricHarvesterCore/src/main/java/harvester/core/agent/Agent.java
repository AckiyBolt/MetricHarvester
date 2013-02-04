package harvester.core.agent;

/**
 *
 * @author Kostiantyn_Belentso
 */
public abstract class Agent
        extends Thread {

    public Agent (String name) {
        super( name );
        this.setDaemon( true );
    }

    @Override
    public abstract void run ();
}