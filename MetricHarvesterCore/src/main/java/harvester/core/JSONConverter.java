package harvester.core;

import com.google.gson.Gson;

/**
 *
 * @author T@urus
 */
public class JSONConverter <T> {
    
    private Gson gson = new Gson();
    
    public String toString (T obj) {
        return gson.toJson( obj );
    }
    
    public T toObject(String json, Class<T> clazz) {
        return gson.fromJson( json, clazz );
    }
}
