package harvester.core.agent;

import java.util.concurrent.RecursiveAction;

/**
 *
 * @author T@urus
 */
public abstract class ActionAgent
        extends RecursiveAction {

    protected final String NAME;

    public ActionAgent ( String name ) {
        this.NAME = name;
    }
}
