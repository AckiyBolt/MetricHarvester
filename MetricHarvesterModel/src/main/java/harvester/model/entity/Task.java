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
    private String clientName;

    public Task () {
    }
}
