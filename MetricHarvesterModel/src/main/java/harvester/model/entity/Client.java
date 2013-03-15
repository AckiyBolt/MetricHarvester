package harvester.model.entity;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import java.io.Serializable;
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
}
