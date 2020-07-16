import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

// https://json.org/example.html

public class JsonOrgExamples extends BaseTestClass {

    @Test
    public void Example1() throws Exception {
        File file = new File(resourcesPath, "example1.json");
        JSONObject expected = new JSONObject(new HashMap<>() {{
            put("glossary", new JSONObject(new HashMap<>() {{
                put("title", "example glossary");
                put("GlossDiv", new JSONObject(new HashMap<>() {{
                    put("title", "S");
                    put("GlossList", new JSONObject(new HashMap<>() {{
                        put("GlossEntry", new JSONObject(new HashMap<>() {{
                            put("ID", "SGML");
                            put("SortAs", "SGML");
                            put("GlossTerm", "Standard Generalized Markup Language");
                            put("Acronym", "SGML");
                            put("Abbrev", "ISO 8879:1986");
                            put("GlossDef", new JSONObject(new HashMap<>() {{
                                put("para", "A meta-markup language, used to create markup languages such as DocBook.");
                                put("GlossSeeAlso", new JSONArray(new ArrayList<>() {{
                                    add("GML");
                                    add("XML");
                                }}));
                            }}));
                            put("GlossSee", "markup");
                        }}));
                    }}));
                }}));
            }}));
        }});
        parseAndCompareObjects("Example1", file, expected);
    }

    @Test
    public void Example2() throws Exception {
        File file = new File(resourcesPath, "example2.json");
        JSONObject expected = new JSONObject(new HashMap<>(){{
            put("menu", new JSONObject(new HashMap<>(){{
                put("id", "file");
                put("value", "File");
                put("popup", new JSONObject(new HashMap<>(){{
                    put("menuitem", new JSONArray(new ArrayList<>(){{
                        add(new JSONObject(new HashMap<>(){{
                            put("value", "New");
                            put("onclick", "CreateNewDoc()");
                        }}));
                        add(new JSONObject(new HashMap<>(){{
                            put("value", "Open");
                            put("onclick", "OpenDoc()");
                        }}));
                        add(new JSONObject(new HashMap<>(){{
                            put("value", "Close");
                            put("onclick", "CloseDoc()");
                        }}));
                    }}));
                }}));
            }}));
        }});
        parseAndCompareObjects("Example2", file, expected);
    }

    @Test
    public void Example3() throws Exception {
        File file = new File(resourcesPath, "example3.json");
        JSONObject expected = new JSONObject(new HashMap<>(){{
            put("widget", new JSONObject(new HashMap<>(){{
                put("debug", "on");
                put("window", new JSONObject(new HashMap<>(){{
                    put("title", "Sample Konfabulator Widget");
                    put("name", "main_window");
                    put("width", 500);
                    put("height", 500);
                }}));
                put("image", new JSONObject(new HashMap<>(){{
                    put("src", "Images/Sun.png");
                    put("name", "sun1");
                    put("hOffset", 250);
                    put("vOffset", 250);
                    put("alignment", "center");
                }}));
                put("text", new JSONObject(new HashMap<>(){{
                    put("data", "Click Here");
                    put("size", 36);
                    put("style", "bold");
                    put("name", "text1");
                    put("hOffset", 250);
                    put("vOffset", 100);
                    put("alignment", "center");
                    put("onMouseUp", "sun1.opacity = (sun1.opacity / 100) * 90;");
                }}));
            }}));
        }});
        parseAndCompareObjects("Example3", file, expected);
    }

    @Test
    public void Example4() throws Exception {
        File file = new File(resourcesPath, "example4.json");
        JSONObject expected = new JSONObject();
        parseAndCompareObjects("Example4", file, expected);
    }

    @Test
    public void Example5() throws Exception {
        File file = new File(resourcesPath, "example5.json");
        JSONObject expected = new JSONObject();
        parseAndCompareObjects("Example5", file, expected);
    }
}
