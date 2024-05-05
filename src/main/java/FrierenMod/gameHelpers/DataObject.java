package FrierenMod.gameHelpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

import static basemod.DevConsole.log;

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
            map.forEach( (k, v) -> log("{" + k + ", " + v + "} loaded") );
        } else {
            log("Failed to load saved data");
        }
    }

    public void clear() {
        dataMap.clear();
    }

    public void putValue(String key, Object value) {
        String boolValue = String.valueOf(value);
        dataMap.put(key, boolValue);
    }

    public boolean getBool(String key) {
        if (dataMap.containsKey(key))
            return Boolean.parseBoolean(dataMap.get(key));
        return false;
    }
    public Object getValue(String key){
        return dataMap.get(key);
    }
}