import org.junit.Test;

import java.io.File;

/**
 * Tests from https://json.org/JSON_checker/
 */
public class JsonOrgChecker extends BaseTestClass {

    private final String resourcesPath = super.resourcesPath + "/checker";

    @Test
    public void Pass1() throws Exception {
        File file = new File(resourcesPath, "pass1.json");
        JSONArray expected = new JSONArray() {{
            add("JSON Test Pattern pass1");
            add(new JSONObject() {{
                put("object with 1 member", new JSONArray() {{ add("array with 1 element"); }});
            }});
            add(new JSONObject());
            add(new JSONArray());
            add(-42);
            add(true);
            add(false);
            add(null);
            add(new JSONObject(){{
                put("integer", 1234567890);
                put("real", -9876.543210);
                put("e", 0.123456789e-12);
                put("E", 1.234567890E+34);
                put("", 23456789012E66);
                put("zero", 0);
                put("one", 1);
                put("space", " ");
                put("quote", "\"");
                put("backslash", "\\");
                put("controls", "\b\f\n\r\t");
                put("alpha", "abcdefghijklmnopqrstuvwyz");
                put("ALPHA", "ABCDEFGHIJKLMNOPQRSTUVWYZ");
                put("digit", "0123456789");
                put("0123456789", "digit");
                put("special", "`1~!@#$%^&*()_+-={':[,]}|;.</>?");
                put("hex", "\u0123\u4567\u89AB\uCDEF\uabcd\uef4A");
                put("true", true);
                put("false", false);
                put("null", null);
                put("array", new JSONArray());
                put("object", new JSONObject());
                put("address", "50 St. James Street");
                put("url", "http://www.JSON.org/");
                put(" s p a c e d ", new JSONArray(){{
                    add(1); add(2); add(3); add(4); add(5); add(6); add(7);
                }});
                put("compact", new JSONArray(){{
                    add(1); add(2); add(3); add(4); add(5); add(6); add(7);
                }});
                put("jsontext", "{\"object with 1 member\":[\"array with 1 element\"]}");
            }});
            add(0.5);
            add(98.6);
            add(99.44);
            add(1066);
            add(1e1);
            add(0.1e1);
            add(1e-1);
            add(1e00);
            add(2e+00);
            add(2e-00);
            add("rosebud");
        }};
        parseAndCompareArrays("Pass1", file, expected);
    }

    @Test
    public void Pass2() throws Exception {
        File file = new File(resourcesPath, "pass2.json");
        JSONArray expected = new JSONArray() {{
            add(new JSONArray(){{
                add(new JSONArray(){{
                    add(new JSONArray(){{
                        add(new JSONArray(){{
                            add(new JSONArray(){{
                                add(new JSONArray(){{
                                    add(new JSONArray(){{
                                        add(new JSONArray(){{
                                            add(new JSONArray(){{
                                                add(new JSONArray(){{
                                                    add(new JSONArray(){{
                                                        add(new JSONArray(){{
                                                            add(new JSONArray(){{
                                                                add(new JSONArray(){{
                                                                    add(new JSONArray(){{
                                                                        add(new JSONArray(){{
                                                                            add(new JSONArray(){{
                                                                                add(new JSONArray(){{
                                                                                    add("Not too deep");
                                                                                }});
                                                                            }});
                                                                        }});
                                                                    }});
                                                                }});
                                                            }});
                                                        }});
                                                    }});
                                                }});
                                            }});
                                        }});
                                    }});
                                }});
                            }});
                        }});
                    }});
                }});
            }});
        }};
        parseAndCompareArrays("Pass2", file, expected);
    }

    @Test
    public void Pass3() throws Exception {
        File file = new File(resourcesPath, "pass3.json");
        JSONObject expected = new JSONObject() {{
            put("JSON Test Pattern pass3", new JSONObject(){{
                put("The outermost value", "must be an object or array.");
                put("In this test", "It is an object.");
            }});
        }};
        parseAndCompareObjects("Pass3", file, expected);
    }

    @Test(expected = ParserException.class)
    public void Fail1() throws Exception {
        File file = new File(resourcesPath, "fail1.json");
        getParser(file).parse();
    }

    @Test(expected = ParserException.class)
    public void Fail2() throws Exception {
        File file = new File(resourcesPath, "fail2.json");
        getParser(file).parse();
    }

    @Test(expected = ParserException.class)
    public void Fail3() throws Exception {
        File file = new File(resourcesPath, "fail3.json");
        getParser(file).parse();
    }

    @Test(expected = ParserException.class)
    public void Fail4() throws Exception {
        File file = new File(resourcesPath, "fail4.json");
        getParser(file).parse();
    }

    @Test(expected = ParserException.class)
    public void Fail5() throws Exception {
        File file = new File(resourcesPath, "fail5.json");
        getParser(file).parse();
    }

    @Test(expected = ParserException.class)
    public void Fail6() throws Exception {
        File file = new File(resourcesPath, "fail6.json");
        getParser(file).parse();
    }

    @Test(expected = ParserException.class)
    public void Fail7() throws Exception {
        File file = new File(resourcesPath, "fail7.json");
        getParser(file).parse();
    }

    @Test(expected = ParserException.class)
    public void Fail8() throws Exception {
        File file = new File(resourcesPath, "fail8.json");
        getParser(file).parse();
    }

    @Test(expected = ParserException.class)
    public void Fail9() throws Exception {
        File file = new File(resourcesPath, "fail9.json");
        getParser(file).parse();
    }

    @Test(expected = ParserException.class)
    public void Fail10() throws Exception {
        File file = new File(resourcesPath, "fail10.json");
        getParser(file).parse();
    }

    @Test(expected = ParserException.class)
    public void Fail11() throws Exception {
        File file = new File(resourcesPath, "fail11.json");
        getParser(file).parse();
    }

    @Test(expected = ParserException.class)
    public void Fail12() throws Exception {
        File file = new File(resourcesPath, "fail12.json");
        getParser(file).parse();
    }

    @Test(expected = ParserException.class)
    public void Fail13() throws Exception {
        File file = new File(resourcesPath, "fail13.json");
        getParser(file).parse();
    }

    @Test(expected = ParserException.class)
    public void Fail14() throws Exception {
        File file = new File(resourcesPath, "fail14.json");
        getParser(file).parse();
    }

    @Test(expected = ParserException.class)
    public void Fail15() throws Exception {
        File file = new File(resourcesPath, "fail15.json");
        getParser(file).parse();
    }

    @Test(expected = ParserException.class)
    public void Fail16() throws Exception {
        File file = new File(resourcesPath, "fail16.json");
        getParser(file).parse();
    }

    @Test(expected = ParserException.class)
    public void Fail17() throws Exception {
        File file = new File(resourcesPath, "fail17.json");
        getParser(file).parse();
    }

    @Test(expected = ParserException.class)
    public void Fail18() throws Exception {
        File file = new File(resourcesPath, "fail18.json");
        getParser(file).parse();
    }

    @Test(expected = ParserException.class)
    public void Fail19() throws Exception {
        File file = new File(resourcesPath, "fail19.json");
        getParser(file).parse();
    }
    @Test(expected = ParserException.class)
    public void Fail20() throws Exception {
        File file = new File(resourcesPath, "fail20.json");
        getParser(file).parse();
    }

    @Test(expected = ParserException.class)
    public void Fail21() throws Exception {
        File file = new File(resourcesPath, "fail21.json");
        getParser(file).parse();
    }

    @Test(expected = ParserException.class)
    public void Fail22() throws Exception {
        File file = new File(resourcesPath, "fail22.json");
        getParser(file).parse();
    }

    @Test(expected = ParserException.class)
    public void Fail23() throws Exception {
        File file = new File(resourcesPath, "fail23.json");
        getParser(file).parse();
    }

    @Test(expected = ParserException.class)
    public void Fail24() throws Exception {
        File file = new File(resourcesPath, "fail24.json");
        getParser(file).parse();
    }

    @Test(expected = ParserException.class)
    public void Fail25() throws Exception {
        File file = new File(resourcesPath, "fail25.json");
        getParser(file).parse();
    }

    @Test(expected = ParserException.class)
    public void Fail26() throws Exception {
        File file = new File(resourcesPath, "fail26.json");
        getParser(file).parse();
    }

    @Test(expected = ParserException.class)
    public void Fail27() throws Exception {
        File file = new File(resourcesPath, "fail27.json");
        getParser(file).parse();
    }

    @Test(expected = ParserException.class)
    public void Fail28() throws Exception {
        File file = new File(resourcesPath, "fail28.json");
        getParser(file).parse();
    }

    @Test(expected = ParserException.class)
    public void Fail29() throws Exception {
        File file = new File(resourcesPath, "fail29.json");
        getParser(file).parse();
    }

    @Test(expected = ParserException.class)
    public void Fail30() throws Exception {
        File file = new File(resourcesPath, "fail30.json");
        getParser(file).parse();
    }

    @Test(expected = ParserException.class)
    public void Fail31() throws Exception {
        File file = new File(resourcesPath, "fail31.json");
        getParser(file).parse();
    }

    @Test(expected = ParserException.class)
    public void Fail32() throws Exception {
        File file = new File(resourcesPath, "fail32.json");
        getParser(file).parse();
    }

}
