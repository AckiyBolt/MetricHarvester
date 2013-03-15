package harvester.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author T@urus
 */
public class JSONConverter <T> {
    
    private Gson gson;

    public JSONConverter () {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        gson = builder.create();
    }
    
    public String toString (T obj) {
        return gson.toJson( obj );
    }
    
    public T toObject(String json, Class<T> clazz) {
        return gson.fromJson( json, clazz );
    }
}
