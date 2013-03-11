package harvester.core.agent;

/**
 *
 * @author Kostiantyn_Belentso
 */
public abstract class TaskAgent <M, T>
        extends ActionAgent {

    public TaskAgent (String name, M monitor ) {
        super( name, monitor );
    }
    
    protected abstract void makeJob (T task);
}