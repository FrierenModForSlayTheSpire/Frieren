package FrierenMod.gameHelpers;

import FrierenMod.utils.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

public class DataObject {
    private final Map<String, String> dataMap;

    public DataObject() {
        dataMap = new HashMap<>();
    }

    public String save() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(dataMap, new TypeToken<Map<String, String>>(){}.getType());
    }

    public void load(String save) {
        Gson gson = new GsonBuilder().create();
        Map<String, String> map = gson.fromJson(save, new TypeToken<Map<String, String>>(){}.getType());
        if (map != null) {
            map.forEach( (k, v) -> Log.logger.info("{{}, {}} loaded", k, v));
        } else {
            Log.logger.info("Failed to load saved data");
        }
    }

    public void clear() {
        dataMap.clear();
    }

    public void putValue(String key, Object value) {
        String boolValue = String.valueOf(value);
        dataMap.put(key, boolValue);
    }

    public boolean containsKey(String key) {
        return dataMap.containsKey(key);
    }
    public String getValue(String key){
        return dataMap.get(key);
    }
}