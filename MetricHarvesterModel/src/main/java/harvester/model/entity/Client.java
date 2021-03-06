package harvester.model.entity;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author T@urus
 */

@Entity
public class Client
        implements Serializable {
    
    @Id
    private ObjectId id;
    private String name;
    private List<Metric> metrics;
    private List<Task> tasks;

    public Client () {
    }

    public ObjectId getId () {
        return id;
    }

    public void setId ( ObjectId id ) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public List<Metric> getMetrics () {
        if (metrics == null)
            metrics = new LinkedList<Metric>();
        return metrics;
    }

    public void setMetrics ( List<Metric> metrics ) {
        this.metrics = metrics;
    }

    public List<Task> getTasks () {
        if (tasks == null)
            tasks = new LinkedList<Task>();
        return tasks;
    }

    public void setTasks ( List<Task> tasks ) {
        this.tasks = tasks;
    }

    @Override
    public int hashCode () {
        int hash = 5;
        hash = 89 * hash + ( this.name != null ? this.name.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals ( Object obj ) {
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        final Client other = ( Client ) obj;
        if ( ( this.name == null ) ? ( other.name != null ) : !this.name.equals( other.name ) )
            return false;
        return true;
    }
}
