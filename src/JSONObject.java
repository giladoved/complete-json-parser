import java.util.HashMap;

class JSONObject extends HashMap<String, Object> {
    public JSONObject getJSONObject(String key) {
        return (JSONObject) get(key);
    }

    public JSONArray getJSONArray(String key) {
        return (JSONArray) get(key);
    }

    public String getString(String key) {
        return (String) get(key);
    }

    public Integer getInteger(String key) {
        return (Integer) get(key);
    }

    public Double getDouble(String key) {
        return (Double) get(key);
    }

    public Boolean getBoolean(String key) {
        return (Boolean) get(key);
    }
}