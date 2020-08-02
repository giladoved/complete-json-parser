import java.util.ArrayList;

class JSONArray extends ArrayList<Object> {
    public JSONObject getJSONObject(int index) {
        return (JSONObject) get(index);
    }

    public JSONArray getJSONArray(int index) {
        return (JSONArray) get(index);
    }

    public String getString(int index) {
        return (String) get(index);
    }

    public Integer getInteger(int index) {
        return (Integer) get(index);
    }

    public Double getDouble(int index) {
        return (Double) get(index);
    }

    public Boolean getBoolean(int index) {
        return (Boolean) get(index);
    }
}