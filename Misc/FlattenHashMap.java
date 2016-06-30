import java.util.HashMap;

/**
 * Created by Kevin on 6/30/16.
 */
public class FlattenHashMap {
    public static void main(String[] args) {
        HashMap<String, Object> testMap = new HashMap<>();
        testMap.put("key1", "a");

        HashMap<String, Object> testMap1 = new HashMap<>();
        testMap1.put("b", "c");
        testMap.put("key2", testMap1);

        HashMap<String, Object> result = flatten(testMap);
        return;
    }

    public static HashMap<String, Object> flatten (HashMap<String, Object> original) {
        HashMap<String, Object> result = new HashMap<>();
        if (original.size() == 0) {
            return result;
        }

        flatten(original, result, "");
        return result;
    }

    public static void flatten(HashMap<String, Object> original, HashMap<String, Object> result,
    String oldKey) {
        for (String key : original.keySet()) {
            Object currObj = original.get(key);
            if (currObj instanceof HashMap) {
                HashMap newMap = (HashMap)currObj;
                flatten(newMap, result, oldKey + key);
            } else {
                result.put(oldKey + key, currObj);
            }
        }
    }
}
