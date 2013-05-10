package harvester.model.entity;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import java.io.Serializable;
import org.bson.types.ObjectId;

/**
 *
 * @author T@urus
 */

@Entity
public class Task
        implements Serializable {
    
    @Id
    private ObjectId id;
    private Long period;
    private String metricName;

    public Task () {
    }

    public ObjectId getId () {
        return id;
    }

    public void setId ( ObjectId id ) {
        this.id = id;
    }

    public Long getPeriod () {
        return period;
    }

    public void setPeriod ( Long period ) {
        this.period = period;
    }

    public String getMetricName () {
        return metricName;
    }

    public void setMetricName ( String metricName ) {
        this.metricName = metricName;
    }

    @Override
    public int hashCode () {
        int hash = 3;
        hash = 97 * hash + ( this.metricName != null ? this.metricName.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals ( Object obj ) {
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        final Task other = ( Task ) obj;
        if ( ( this.metricName == null ) ? ( other.metricName != null ) : !this.metricName.equals( other.metricName ) )
            return false;
        return true;
    }

    @Override
    public String toString () {
        return metricName;
    }
}