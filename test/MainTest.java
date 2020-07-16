import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests frames from https://github.com/nst/JSONTestSuite/
 */

public class MainTest {

// Gilad tests

//    @Test
//    public void ArrayString() throws Exception {
//        String input = "[\"the json pahsah\"]";
//        JSONArray expected = new JSONArray(new ArrayList<>() {{
//            add("the json pahsah");
//        }});
//        assert parseAndCompareArray(input, expected);
//    }
//
//    @Test
//    public void ArrayOne() throws Exception {
//        String input = "[1]";
//        JSONArray expected = new JSONArray(new ArrayList<>() {{
//            add(1);
//        }});
//        assert parseAndCompareArray(input, expected);
//    }
//
//    @Test
//    public void ArrayTwo() throws Exception {
//        String input = "[1, 2]";
//        JSONArray expected = new JSONArray(new ArrayList<>() {{
//            add(1);
//            add(2);
//        }});
//        assert parseAndCompareArray(input, expected);
//    }
//
//    @Test(expected = Exception.class)
//    public void ArrayOneComma() throws Exception {
//        String input = "[,]";
//        JSONArray expected = new JSONArray();
//        assert parseAndCompareArray(input, expected);
//    }
//
//    @Test(expected = Exception.class)
//    public void ArrayDoubleComma() throws Exception {
//        String input = "[1,, 2]";
//        JSONArray expected = new JSONArray(new ArrayList<>() {{
//            add(1);
//            add(2);
//        }});
//        assert parseAndCompareArray(input, expected);
//    }
//
//    @Test(expected = Exception.class)
//    public void ArrayTrailingComma() throws Exception {
//        String input = "[1,2,]";
//        JSONArray expected = new JSONArray(new ArrayList<>() {{
//            add(1);
//            add(2);
//        }});
//        assert parseAndCompareArray(input, expected);
//    }
//
//    @Test()
//    public void ArrayOneNested() throws Exception {
//        String input = "[1,[]]";
//        JSONArray expected = new JSONArray(new ArrayList<>() {{
//            add(1);
//            add(new JSONArray());
//        }});
//        assert parseAndCompareArray(input, expected);
//    }
//
//    @Test()
//    public void ObjectEmpty() throws Exception {
//        String input = "{}";
//        JSONObject expected = new JSONObject();
//        assert parseAndCompareObjects(input, expected);
//    }
//
//    @Test()
//    public void ObjectOneString() throws Exception {
//        String input = "{\"mykey\": \"myvalue\"}";
//        JSONObject expected = new JSONObject(new HashMap<>() {{
//            put("mykey", "myvalue");
//        }});
//        assert parseAndCompareObjects(input, expected);
//    }
//
//    @Test()
//    public void ObjectTwoStrings() throws Exception {
//        String input = "{\"mykey\": \"myvalue\", \"key2\": \"value2\"}";
//        JSONObject expected = new JSONObject(new HashMap<>() {{
//            put("mykey", "myvalue");
//            put("key2", "value2");
//        }});
//        assert parseAndCompareObjects(input, expected);
//    }
//
//    @Test()
//    public void ObjectOneInt() throws Exception {
//        String input = "{\"mykey\": 2 }";
//        JSONObject expected = new JSONObject(new HashMap<>() {{
//            put("mykey", 2);
//        }});
//        assert parseAndCompareObjects(input, expected);
//    }
//
//    @Test()
//    public void ObjectTwoInts() throws Exception {
//        String input = "{\"mykey\": 2, \"key2\": -99}";
//        JSONObject expected = new JSONObject(new HashMap<>() {{
//            put("mykey", 2);
//            put("key2", -99);
//        }});
//        assert parseAndCompareObjects(input, expected);
//    }
//
//    @Test()
//    public void ArrayOfObject() throws Exception {
//        String input = "[{\"mykey\": 2}]";
//        JSONArray expected = new JSONArray(new ArrayList<>() {{
//            add(new JSONObject(new HashMap<>() {{
//                put("mykey", 2);
//            }}));
//        }});
//        assert parseAndCompareArray(input, expected);
//    }
//
//    @Test()
//    public void ArrayOfObjectWithArray() throws Exception {
//        String input = "[{\"mykey\": [2, 4]}]";
//        JSONArray expected = new JSONArray(new ArrayList<>() {{
//            add(new JSONObject(new HashMap<>() {{
//                put("mykey", new JSONArray(new ArrayList<>() {{
//                    add(2);
//                    add(4);
//                }}));
//            }}));
//        }});
//        assert parseAndCompareArray(input, expected);
//    }
//
//    @Test()
//    public void ArrayOfObjects() throws Exception {
//        String input = "[{\"mykey\": 2}, {\"key2\": \"val\"}]";
//        JSONArray expected = new JSONArray(new ArrayList<>() {{
//            add(new JSONObject(new HashMap<>() {{
//                put("mykey", 2);
//            }}));
//            add(new JSONObject(new HashMap<>() {{
//                put("key2", "val");
//            }}));
//        }});
//        assert parseAndCompareArray(input, expected);
//    }
}