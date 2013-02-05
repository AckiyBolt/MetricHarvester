package harvester.core.agent;

import java.util.concurrent.RecursiveTask;

/**
 *
 * @author T@urus
 */
public abstract class TaskAgent<T>
        extends RecursiveTask<T> {

    protected final String NAME;

    public TaskAgent ( String name ) {
        this.NAME = name;
    }
}
