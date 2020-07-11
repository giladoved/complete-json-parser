import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

//public abstract class JSON {
//}
//
//class JSONString extends JSON {
//    String string;
//
//    public JSONString(String str) {
//        this.string = str;
//    }
//
//    @Override
//    public String toString() {
//        return string;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        JSONString that = (JSONString) o;
//        return Objects.equals(string, that.string);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(string);
//    }
//}
//
//class JSONNumber extends JSON {
//    int number;
//
//    public JSONNumber(String numStr) {
//        this.number = Integer.parseInt(numStr);
//    }
//
//    public JSONNumber(int num) {
//        this.number = num;
//    }
//
//    @Override
//    public String toString() {
//        return number + "";
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        JSONNumber that = (JSONNumber) o;
//        return number == that.number;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(number);
//    }
//}
//
//class JSONTrue extends JSON {
//    @Override
//    public String toString() {
//        return "true";
//    }
//}
//
//class JSONFalse extends JSON {
//    @Override
//    public String toString() {
//        return "false";
//    }
//}
//
//class JSONNull extends JSON {
//    @Override
//    public String toString() {
//        return "null";
//    }
//}
//

class JSONArray {
    ArrayList<Object> array;

    public JSONArray() {
        array = new ArrayList<>();
    }

    public JSONArray(ArrayList<Object> array) {
        this.array = array;
    }

    public void add(Object item) {
        array.add(item);
    }

    public int size() {
        return array.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < array.size(); i++) {
            sb.append(array.get(i));
            if (i != array.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JSONArray jsonArray = (JSONArray) o;
        return Objects.equals(array, jsonArray.array);
    }

    @Override
    public int hashCode() {
        return Objects.hash(array);
    }
}

class JSONObject {
    HashMap<String, Object> map;

    public JSONObject() {
        map = new HashMap<>();
    }

    public JSONObject(HashMap<String, Object> map) {
        this.map = map;
    }

    public int size() {
        return map.keySet().size();
    }

    public void put(String key, Object val) {
        map.put(key, val);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('\n');
        for (String key : map.keySet()) {
            sb.append(key)
                    .append(": ")
                    .append(map.get(key))
                    .append('\n');
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JSONObject that = (JSONObject) o;
        return Objects.equals(map, that.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map);
    }
}
