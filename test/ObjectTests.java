import org.junit.Test;

public class ObjectTests extends BaseTestClass {
    @Test()
    public void ObjectBasic() throws Exception {
        String input = "{\"asd\":\"sdf\"}";
        JSONObject expected = new JSONObject() {{
            put("asd", "sdf");
        }};
        parseAndCompareObjects("ObjectBasic", input, expected);
    }

    @Test()
    public void ObjectTwoInts() throws Exception {
        String input = "{\"mykey\": 2, \"key2\": -99}";
        JSONObject expected = new JSONObject() {{
            put("mykey", 2);
            put("key2", -99);
        }};
        parseAndCompareObjects("ObjectTwoInts", input, expected);
    }

    @Test()
    public void ObjectTwoStrings() throws Exception {
        String input = "{\"asd\":\"sdf\", \"dfg\":\"fgh\"}";
        JSONObject expected = new JSONObject() {{
            put("asd", "sdf");
            put("dfg", "fgh");
        }};
        parseAndCompareObjects("ObjectTwoStrings", input, expected);
    }

    @Test()
    public void ObjectDuplicateKeys() throws Exception {
        String input = "{\"a\":\"b\",\"a\":\"c\"}";
        JSONObject expected = new JSONObject() {{
            put("a", "c");
        }};
        parseAndCompareObjects("ObjectDuplicateKeys", input, expected);
    }

    @Test()
    public void ObjectDuplicateItems() throws Exception {
        String input = "{\"a\":\"b\",\"a\":\"b\"}";
        JSONObject expected = new JSONObject() {{
            put("a", "b");
        }};
        parseAndCompareObjects("ObjectDuplicateItems", input, expected);
    }

    @Test()
    public void ObjectEmpty() throws Exception {
        String input = "{}";
        JSONObject expected = new JSONObject();
        parseAndCompareObjects("ObjectEmpty", input, expected);
    }

    @Test()
    public void ObjectEmptySpace() throws Exception {
        String input = "{\n \n \n}";
        JSONObject expected = new JSONObject();
        parseAndCompareObjects("ObjectEmpty", input, expected);
    }

    @Test()
    public void ObjectLeading() throws Exception {
        String input = "{ \"asd\":\"sdf\"}";
        JSONObject expected = new JSONObject() {{
            put("asd", "sdf");
        }};
        parseAndCompareObjects("ObjectLeading", input, expected);
    }

    @Test()
    public void ObjectTrailing() throws Exception {
        String input = "{\"asd\" :\"sdf\"}";
        JSONObject expected = new JSONObject() {{
            put("asd", "sdf");
        }};
        parseAndCompareObjects("ObjectTrailing", input, expected);
    }

    @Test()
    public void ObjectLeadingAndTrailing() throws Exception {
        String input = "{ \"asd\" :\"sdf\"}";
        JSONObject expected = new JSONObject() {{
            put("asd", "sdf");
        }};
        parseAndCompareObjects("ObjectLeadingAndTrailing", input, expected);
    }

    @Test()
    public void ObjectLeadingMultiple() throws Exception {
        String input = "{\n   \"asd\":\"sdf\"}";
        JSONObject expected = new JSONObject() {{
            put("asd", "sdf");
        }};
        parseAndCompareObjects("ObjectLeadingMultiple", input, expected);
    }

    @Test()
    public void ObjectTrailingMultiple() throws Exception {
        String input = "{\"asd\"   \n:\"sdf\"}";
        JSONObject expected = new JSONObject() {{
            put("asd", "sdf");
        }};
        parseAndCompareObjects("ObjectTrailingMultiple", input, expected);
    }

    @Test()
    public void ObjectLeadingAndTrailingMultiple() throws Exception {
        String input = "{\n   \"asd\"  \n:\"sdf\"}";
        JSONObject expected = new JSONObject() {{
            put("asd", "sdf");
        }};
        parseAndCompareObjects("ObjectLeadingAndTrailingMultiple", input, expected);
    }

    @Test()
    public void ObjectEmptyKey() throws Exception {
        String input = "{\"\":0}";
        JSONObject expected = new JSONObject() {{
            put("", 0);
        }};
        parseAndCompareObjects("ObjectEmptyKey", input, expected);
    }

    @Test()
    public void ObjectEscapedNullKey() throws Exception {
        String input = "{\"foo\\u0000bar\": 42}";
        JSONObject expected = new JSONObject() {{
            put("foo\u0000bar", 42);
        }};
        parseAndCompareObjects("ObjectEscapedNullKey", input, expected);
    }

    @Test()
    public void ObjectExtremeNumbers() throws Exception {
        String input = "{ \"min\": -1.0e+28, \"max\": 1.0e+28 }";
        JSONObject expected = new JSONObject() {{
            put("min", -1.0e+28);
            put("max", 1.0e+28);
        }};
        parseAndCompareObjects("ObjectExtremeNumbers", input, expected);
    }

    @Test()
    public void ObjectLongStrings() throws Exception {
        String input = "{\"x\":[{\"id\": \"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\"}], \"id\": \"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\"}";
        JSONObject expected = new JSONObject() {{
            put("x", new JSONArray() {{
                add(new JSONObject() {{
                    put("id", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                }});
            }});
            put("id", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        }};
        parseAndCompareObjects("ObjectLongStrings", input, expected);
    }

    @Test()
    public void ObjectEmptyArray() throws Exception {
        String input = "{\"a\":[]}";
        JSONObject expected = new JSONObject() {{
            put("a", new JSONArray());
        }};
        parseAndCompareObjects("ObjectEmptyArray", input, expected);
    }

    @Test()
    public void ObjectStringUnicode() throws Exception {
        String input = "{\"title\":\"\\u041f\\u043e\\u043b\\u0442\\u043e\\u0440\\u0430 \\u0417\\u0435\\u043c\\u043b\\u0435\\u043a\\u043e\\u043f\\u0430\" }";
        JSONObject expected = new JSONObject() {{
            put("title", "Полтора Землекопа");
        }};
        parseAndCompareObjects("ObjectStringUnicode", input, expected);
    }

    @Test()
    public void ObjectNewlines() throws Exception {
        String input = "{\n" +
                "\"a\": \"b\"\n" +
                "}";
        JSONObject expected = new JSONObject() {{
            put("a", "b");
        }};
        parseAndCompareObjects("ObjectNewlines", input, expected);
    }


    @Test(expected = ParserException.class)
    public void ObjectBadValue() throws Exception {
        String input = "[\"x\", truth]";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectBracketKey() throws Exception {
        String input = "{[: \"x\"}";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectCommaNotColon() throws Exception {
        String input = "{\"x\", null}";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectDoubleColon() throws Exception {
        String input = "{\"x\"::\"b\"}";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectEmoji() throws Exception {
        String input = "{\uD83C\uDDE8\uD83C\uDDED}";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectGarbageAtEnd() throws Exception {
        String input = "{\"a\":\"a\" 123}";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectSingleQuotes() throws Exception {
        String input = "{key: 'value'";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectLoneContinuationByteKeyTrailingComma() throws Exception {
        String input = "{\"�\":\"0\",}";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectMissingColon() throws Exception {
        String input = "{\"a\" b}";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectMissingKey() throws Exception {
        String input = "{:\"b\"}";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectMissingValue() throws Exception {
        String input = "{\"a\":";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectNoColonAndValue() throws Exception {
        String input = "{\"a\"";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectNonStringKey() throws Exception {
        String input = "{1:1}";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectHugeIntKey() throws Exception {
        String input = "{9999E9999:1}";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectRepeatedNull() throws Exception {
        String input = "{null:null,null:null}";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectTrailingCommas() throws Exception {
        String input = "{\"id\":0,,,,,}";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectSingleQuote() throws Exception {
        String input = "{'a':0}";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectTrailingComma() throws Exception {
        String input = "{\"id\":0,}";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectTrailingComment() throws Exception {
        String input = "{\"a\":\"b\"}/**/";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectTrailingCommentOpen() throws Exception {
        String input = "{\"a\":\"b\"}/**//";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectTrailingCommentSlashOpen() throws Exception {
        String input = "{\"a\":\"b\"}//";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectTrailingCommentSlashOpenIncomplete() throws Exception {
        String input = "{\"a\":\"b\"}/";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectDoubleComma() throws Exception {
        String input = "{\"a\":\"b\",,\"c\":\"d\"}";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectNoQuotesKey() throws Exception {
        String input = "{a: \"b\"}";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectUnterminatedValue() throws Exception {
        String input = "{\"a\":\"a";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectSingleString() throws Exception {
        String input = "{ \"foo\" : \"bar\", \"a\" }";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectTrailingGarbage() throws Exception {
        String input = "{\"a\":\"b\"}#";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectCommaNotClosingBrace() throws Exception {
        String input = "{\"x\": true,";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectExtraClosing() throws Exception {
        String input = "{}}";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectNoValNoClose() throws Exception {
        String input = "{\"\":";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectComment() throws Exception {
        String input = "{\"a\":/*comment*/\"b\"}";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectTrailingString() throws Exception {
        String input = "{\"a\": true} \"x\"";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectOpen() throws Exception {
        String input = "{";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectOpenArrayClose() throws Exception {
        String input = "{]";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectOpenComma() throws Exception {
        String input = "{,";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectOpenArrayOpen() throws Exception {
        String input = "{[";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectOpenString() throws Exception {
        String input = "{\"a";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectOpenStringApostrophes() throws Exception {
        String input = "{'a'";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectOpenOpen() throws Exception {
        String input = "[\"\\{[\"\\{[\"\\{[\"\\{";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectTrailingHashtag() throws Exception {
        String input = "{\"a\":\"b\"}#{}";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectUnescapedLF() throws Exception {
        String input = "\\u000A\"\"";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ObjectUnclosedObj() throws Exception {
        String input = "{\"asd\":\"asd\"";
        parse(input);
    }
}
