import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

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

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void ArrayWithSpaces() throws Exception {
        String input = "[[]   ]";
        JSONArray expected = new JSONArray(new ArrayList<>() {{
            add(new JSONArray());
        }});
        parseAndCompareArray("ArrayWithSpaces", input, expected);
    }

    @Test
    public void ArrayEmptyString() throws Exception {
        String input = "[\"\"]";
        JSONArray expected = new JSONArray(new ArrayList<>() {{
            add("");
        }});
        parseAndCompareArray("ArrayEmptyString", input, expected);
    }

    @Test
    public void ArrayEmpty() throws Exception {
        String input = "[]";
        JSONArray expected = new JSONArray();
        parseAndCompareArray("ArrayEmpty", input, expected);
    }

    @Test
    public void ArrayEndingWithNewline() throws Exception {
        String input = "[\"a\"]\n";
        JSONArray expected = new JSONArray(new ArrayList<>() {{
            add("a");
        }});
        parseAndCompareArray("ArrayEndingWithNewline", input, expected);
    }

    @Test
    public void ArrayFalse() throws Exception {
        String input = "[false]";
        JSONArray expected = new JSONArray(new ArrayList<>() {{
            add(false);
        }});
        parseAndCompareArray("ArrayFalse", input, expected);
    }

    @Test
    public void ArrayTrue() throws Exception {
        String input = "[true]";
        JSONArray expected = new JSONArray(new ArrayList<>() {{
            add(true);
        }});
        parseAndCompareArray("ArrayTrue", input, expected);
    }

    @Test
    public void ArrayNull() throws Exception {
        String input = "[null]";
        JSONArray expected = new JSONArray(new ArrayList<>() {{
            add(new Null());
        }});
        parseAndCompareArray("ArrayNull", input, expected);
    }

    @Test
    public void ArrayHeterogeneous() throws Exception {
        String input = "[null, 1, \"1\", {}]";
        JSONArray expected = new JSONArray(new ArrayList<>() {{
            add(new Null());
            add(1);
            add("1");
            add(new JSONObject());
        }});
        parseAndCompareArray("ArrayHeterogeneous", input, expected);
    }

    @Test
    public void ArrayOneWithNewline() throws Exception {
        String input = "[1\n" +
                "]";
        JSONArray expected = new JSONArray(new ArrayList<>() {{
            add(1);
        }});
        parseAndCompareArray("ArrayOneWithNewline", input, expected);
    }

    @Test
    public void ArrayLeadingSpace() throws Exception {
        String input = " [1]";
        JSONArray expected = new JSONArray(new ArrayList<>() {{
            add(1);
        }});
        parseAndCompareArray("ArrayLeadingSpace", input, expected);
    }

    @Test
    public void ArrayManyNulls() throws Exception {
        String input = "[1,null,null,null,2]";
        JSONArray expected = new JSONArray(new ArrayList<>() {{
            add(1);
            add(new Null());
            add(new Null());
            add(new Null());
            add(2);
        }});
        parseAndCompareArray("ArrayManyNulls", input, expected);
    }

    @Test
    public void ArrayTrailingSpace() throws Exception {
        String input = "[1] ";
        JSONArray expected = new JSONArray(new ArrayList<>() {{
            add(1);
        }});
        parseAndCompareArray("ArrayTrailingSpace", input, expected);
    }

    @Test
    public void ArrayString() throws Exception {
        String input = "[\"abc\"]";
        JSONArray expected = new JSONArray(new ArrayList<>() {{
            add("abc");
        }});
        parseAndCompareArray("ArrayString", input, expected);
    }

    @Test
    public void ArrayStringLeadingSpace() throws Exception {
        String input = "[ \"abc\"]";
        JSONArray expected = new JSONArray(new ArrayList<>() {{
            add("abc");
        }});
        parseAndCompareArray("ArrayStringLeadingSpace", input, expected);
    }

    @Test
    public void ArrayStringWhitespace() throws Exception {
        String input = " [] ";
        JSONArray expected = new JSONArray();
        parseAndCompareArray("ArrayStringWhitespace", input, expected);
    }







    @Test
    public void NumberInteger() throws Exception {
        String input = "51";
        Integer expected = 51;
        parseAndCompareIntegers("NumberInteger", input, expected);
    }

    @Test
    public void NumberNegativeDouble() throws Exception {
        String input = "-0.3";
        Double expected = -0.3;
        parseAndCompareDoubles("NegativeDouble", input, expected);
    }

    @Test
    public void NumberExponent() throws Exception {
        String input = "123e65";
        double expected = 123e65;
        parseAndCompareDoubles("NumberExponent", input, expected);
    }

    @Test
    public void NumberExponent0EPlus1() throws Exception {
        String input = "0e+1";
        double expected = 0e+1;
        parseAndCompareDoubles("NumberExponent0EPlus1", input, expected);
    }

    @Test
    public void NumberExponent0E1() throws Exception {
        String input = "0e1";
        double expected = 0e1;
        parseAndCompareDoubles("NumberExponent0E1", input, expected);
    }

    @Test
    public void NumberAfterSpace() throws Exception {
        String input = "[ 4]";
        JSONArray expected = new JSONArray(new ArrayList<>() {{
            add(4);
        }});
        parseAndCompareArray("NumberAfterSpace", input, expected);
    }

    @Test
    public void NumberCloseToZero() throws Exception {
        String input = "-0.000000000000000000000000000000000000000000000000000000000000000000000000000001";
        double expected = -0.000000000000000000000000000000000000000000000000000000000000000000000000000001;
        parseAndCompareDoubles("NumberCloseToZero", input, expected);
    }

    @Test
    public void NumberExponentOne() throws Exception {
        String input = "20e1";
        double expected = 20e1;
        parseAndCompareDoubles("NumberExponentOne", input, expected);
    }

    @Test
    public void NumberNegativeZero() throws Exception {
        String input = "-0";
        int expected = 0;
        parseAndCompareIntegers("NumberNegativeZero", input, expected);
    }

    @Test
    public void NumberNegativeInt() throws Exception {
        String input = "-123";
        int expected = -123;
        parseAndCompareIntegers("NumberNegativeInt", input, expected);
    }

    @Test
    public void NumberNegativeOne() throws Exception {
        String input = "-1";
        int expected = -1;
        parseAndCompareIntegers("NumberNegativeOne", input, expected);
    }

    @Test
    public void NumberCapitalE() throws Exception {
        String input = "1E22";
        double expected = 1E22;
        parseAndCompareDoubles("NumberCapitalE", input, expected);
    }

    @Test
    public void NumberCapitalENegative() throws Exception {
        String input = "1E-2";
        double expected = 1E-2;
        parseAndCompareDoubles("NumberCapitalENegative", input, expected);
    }

    @Test
    public void NumberCapitalEPositive() throws Exception {
        String input = "1E+2";
        double expected = 1E+2;
        parseAndCompareDoubles("NumberCapitalEPositive", input, expected);
    }

    @Test
    public void NumberFractionExp() throws Exception {
        String input = "123.456e78";
        double expected = 123.456e78;
        parseAndCompareDoubles("NumberFractionExp", input, expected);
    }

    @Test
    public void NumberNegativeExp() throws Exception {
        String input = "1e-2";
        double expected = 1e-2;
        parseAndCompareDoubles("NumberNegativeExp", input, expected);
    }

    @Test
    public void NumberPositiveExp() throws Exception {
        String input = "1e+2";
        double expected = 1e+2;
        parseAndCompareDoubles("NumberPositiveExp", input, expected);
    }

    @Test
    public void NumberFraction() throws Exception {
        String input = "123.456789";
        double expected = 123.456789;
        parseAndCompareDoubles("NumberFraction", input, expected);
    }





    @Test()
    public void ObjectBasic() throws Exception {
        String input = "{\"asd\":\"sdf\"}";
        JSONObject expected = new JSONObject(new HashMap<>() {{
            put("asd", "sdf");
        }});
        parseAndCompareObjects("ObjectBasic", input, expected);
    }

    @Test()
    public void ObjectTwoInts() throws Exception {
        String input = "{\"mykey\": 2, \"key2\": -99}";
        JSONObject expected = new JSONObject(new HashMap<>() {{
            put("mykey", 2);
            put("key2", -99);
        }});
        parseAndCompareObjects("ObjectTwoInts", input, expected);
    }

    @Test()
    public void ObjectTwoStrings() throws Exception {
        String input = "{\"asd\":\"sdf\", \"dfg\":\"fgh\"}";
        JSONObject expected = new JSONObject(new HashMap<>() {{
            put("asd", "sdf");
            put("dfg", "fgh");
        }});
        parseAndCompareObjects("ObjectTwoStrings", input, expected);
    }

    @Test()
    public void ObjectDuplicateKeys() throws Exception {
        String input = "{\"a\":\"b\",\"a\":\"c\"}";
        JSONObject expected = new JSONObject(new HashMap<>() {{
            put("a", "c");
        }});
        parseAndCompareObjects("ObjectDuplicateKeys", input, expected);
    }

    @Test()
    public void ObjectDuplicateItems() throws Exception {
        String input = "{\"a\":\"b\",\"a\":\"b\"}";
        JSONObject expected = new JSONObject(new HashMap<>() {{
            put("a", "b");
        }});
        parseAndCompareObjects("ObjectDuplicateItems", input, expected);
    }

    @Test()
    public void ObjectEmpty() throws Exception {
        String input = "{}";
        JSONObject expected = new JSONObject();
        parseAndCompareObjects("ObjectEmpty", input, expected);
    }

    @Test()
    public void ObjectEmptyKey() throws Exception {
        String input = "{\"\":0}";
        JSONObject expected = new JSONObject(new HashMap<>() {{
            put("", 0);
        }});
        parseAndCompareObjects("ObjectEmptyKey", input, expected);
    }

    @Test()
    public void ObjectEscapedNullKey() throws Exception {
        String input = "{\"foo\\u0000bar\": 42}";
        JSONObject expected = new JSONObject(new HashMap<>() {{
            put("foo\u0000bar", 42);
        }});
        parseAndCompareObjects("ObjectEscapedNullKey", input, expected);
    }

    @Test()
    public void ObjectExtremeNumbers() throws Exception {
        String input = "{ \"min\": -1.0e+28, \"max\": 1.0e+28 }";
        JSONObject expected = new JSONObject(new HashMap<>() {{
            put("min", -1.0e+28);
            put("max", 1.0e+28);
        }});
        parseAndCompareObjects("ObjectExtremeNumbers", input, expected);
    }

    @Test()
    public void ObjectLongStrings() throws Exception {
        String input = "{\"x\":[{\"id\": \"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\"}], \"id\": \"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\"}";
        JSONObject expected = new JSONObject(new HashMap<>() {{
            put("x", new JSONArray(new ArrayList<>() {{
                add(new JSONObject(new HashMap<>() {{
                    put("id", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                }}));
            }}));
            put("id", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        }});
        parseAndCompareObjects("ObjectLongStrings", input, expected);
    }

    @Test()
    public void ObjectEmptyArray() throws Exception {
        String input = "{\"a\":[]}";
        JSONObject expected = new JSONObject(new HashMap<>() {{
            put("a", new JSONArray());
        }});
        parseAndCompareObjects("ObjectEmptyArray", input, expected);
    }

    @Test()
    public void ObjectStringUnicode() throws Exception {
        String input = "{\"title\":\"\\u041f\\u043e\\u043b\\u0442\\u043e\\u0440\\u0430 \\u0417\\u0435\\u043c\\u043b\\u0435\\u043a\\u043e\\u043f\\u0430\" }";
        JSONObject expected = new JSONObject(new HashMap<>() {{
            put("title", "Полтора Землекопа");
        }});
        parseAndCompareObjects("ObjectStringUnicode", input, expected);
    }

    @Test()
    public void ObjectNewlines() throws Exception {
        String input = "{\n" +
                "\"a\": \"b\"\n" +
                "}";
        JSONObject expected = new JSONObject(new HashMap<>() {{
            put("a", "b");
        }});
        parseAndCompareObjects("ObjectNewlines", input, expected);
    }






    @Test
    public void String() throws Exception {
        String input = "\"cornflakes\"";
        String expected = "cornflakes";
        parseAndCompareStrings("String", input, expected);
    }

    @Test
    public void StringEmpty() throws Exception {
        String input = "\"\"";
        String expected = "";
        parseAndCompareStrings("Empty", input, expected);
    }

    @Test()
    public void StringUnicodeByteLengths() throws Exception {
        String input = "\"\\u0060\\u012a\\u12AB\"";
        String expected = "`Īካ";
        parseAndCompareStrings("StringUnicodeByteLengths", input, expected);
    }

    @Test()
    public void StringSurrogatePair() throws Exception {
        String input = "\"\\uD801\\udc37\"";
        String expected = "𐐷";
        parseAndCompareStrings("StringSurrogatePair", input, expected);
    }

    @Test()
    public void StringSurrogatePairs() throws Exception {
        String input = "\"\\ud83d\\ude39\\ud83d\\udc8d\"";
        String expected = "\uD83D\uDE39\uD83D\uDC8D";
        parseAndCompareStrings("StringSurrogatePairs", input, expected);
    }

    @Test()
    public void StringAllowedEscapes() throws Exception {
        String input = "\"\\\"\\\\\\/\\b\\f\\n\\r\\t\"";
        String expected = "\"\\/\b\f\n\r\t";
        parseAndCompareStrings("StringAllowedEscapes", input, expected);
    }

    @Test()
    public void StringBackslashNull() throws Exception {
        String input = "\"\\\\u0000\"";
        String expected = "\\u0000";
        parseAndCompareStrings("StringBackslashNull", input, expected);
    }

    @Test()
    public void StringBackslashQuotes() throws Exception {
        String input = "\"\\\"\"";
        String expected = "\"";
        parseAndCompareStrings("StringBackslashQuotes", input, expected);
    }

    @Test()
    public void StringDoubleEscapeA() throws Exception {
        String input = "\"\\\\a\"";
        String expected = "\\a";
        parseAndCompareStrings("StringDoubleEscapeA", input, expected);
    }

    @Test()
    public void StringDoubleEscapeN() throws Exception {
        String input = "\"\\\\n\"";
        String expected = "\\n";
        parseAndCompareStrings("StringDoubleEscapeN", input, expected);
    }

    @Test()
    public void StringEscapeCtrlChar() throws Exception {
        String input = "\"\\u0012\"";
        String expected = "\u0012";
        parseAndCompareStrings("StringEscapeCtrlChar", input, expected);
    }

    @Test()
    public void StringEscapeNonChar() throws Exception {
        String input = "\"\\uFFFF\"";
        String expected = "\uFFFF";
        parseAndCompareStrings("StringEscapeNonChar", input, expected);
    }

    @Test()
    public void StringLastSurrogates12() throws Exception {
        String input = "\"\\uDBFF\\uDFFF\"";
        String expected = "\uDBFF\uDFFF";
        parseAndCompareStrings("StringLastSurrogates12", input, expected);
    }

    @Test()
    public void StringNbsp() throws Exception {
        String input = "\"new\\u00A0line\"";
        String expected = "new line";
        parseAndCompareStrings("StringNbsp", input, expected);
    }

    @Test()
    public void String10FFFF() throws Exception {
        String input = "\"\uDBFF\uDFFF\"";
        String expected = "\uDBFF\uDFFF";
        parseAndCompareStrings("String10FFFF", input, expected);
    }

    @Test()
    public void StringFFFF() throws Exception {
        String input = "\"\uFFFF\"";
        String expected = "\uFFFF";
        parseAndCompareStrings("StringFFFF", input, expected);
    }

    @Test()
    public void StringEscapedNull() throws Exception {
        String input = "\"\\u0000\"";
        String expected = "\u0000";
        parseAndCompareStrings("StringEscapedNull", input, expected);
    }

    @Test()
    public void StringOneByte() throws Exception {
        String input = "\"\\u002c\"";
        String expected = "\u002c";
        parseAndCompareStrings("StringOneByte", input, expected);
    }

    @Test()
    public void StringPi() throws Exception {
        String input = "\"π\"";
        String expected = "π";
        parseAndCompareStrings("StringPi", input, expected);
    }

    @Test()
    public void StringReservedChar() throws Exception {
        String input = "\"\uD82F\uDFFF\"";
        String expected = "\uD82F\uDFFF";
        parseAndCompareStrings("StringReservedChar", input, expected);
    }

    @Test()
    public void StringAscii() throws Exception {
        String input = "\"asd \"";
        String expected = "asd ";
        parseAndCompareStrings("StringAscii", input, expected);
    }

    @Test()
    public void StringSpace() throws Exception {
        String input = "\" \"";
        String expected = " ";
        parseAndCompareStrings("StringSpace", input, expected);
    }

    @Test()
    public void StringSurrogateGClef() throws Exception {
        String input = "\"\\uD834\\uDd1e\"";
        String expected = "\uD834\uDd1e";
        parseAndCompareStrings("StringSurrogateGClef", input, expected);
    }

    @Test()
    public void StringTwoByte() throws Exception {
        String input = "\"\\u0123\"";
        String expected = "\u0123";
        parseAndCompareStrings("StringTwoByte", input, expected);
    }

    @Test()
    public void StringThreeByte() throws Exception {
        String input = "\"\\u0821\"";
        String expected = "\u0821";
        parseAndCompareStrings("StringThreeByte", input, expected);
    }

    @Test()
    public void StringLineSep() throws Exception {
        String input = "\"\\u2028\"";
        String expected = "\u2028";
        parseAndCompareStrings("StringLineSep", input, expected);
    }

    @Test()
    public void StringParSep() throws Exception {
        String input = "\"\\u2029\"";
        String expected = "\u2029";
        parseAndCompareStrings("StringParSep", input, expected);
    }

    @Test()
    public void StringUEscape() throws Exception {
        String input = "\"\\u0061\\u30af\\u30EA\\u30b9\"";
        String expected = "\u0061\u30af\u30EA\u30b9";
        parseAndCompareStrings("StringUEscape", input, expected);
    }

    @Test()
    public void StringNewline() throws Exception {
        String input = "\"new\\u000Aline\"";
        String expected = "new\nline";
        parseAndCompareStrings("StringNewline", input, expected);
    }

    @Test()
    public void StringCharDel() throws Exception {
        String input = "\"\u007F\"";
        String expected = "";
        parseAndCompareStrings("StringCharDel", input, expected);
    }

    @Test()
    public void StringUnicode() throws Exception {
        String input = "\"\\uA66D\"";
        String expected = "ꙭ";
        parseAndCompareStrings("StringUnicode", input, expected);
    }

    @Test()
    public void StringEscapedBackslash() throws Exception {
        String input = "\"\\u005C\"";
        String expected = "\\";
        parseAndCompareStrings("StringEscapedBackslash", input, expected);
    }

    @Test()
    public void StringUnicode2() throws Exception {
        String input = "\"⍂㈴⍂\"";
        String expected = "⍂㈴⍂";
        parseAndCompareStrings("StringUnicode2", input, expected);
    }

    @Test
    public void String10FFFE() throws Exception {
        String input = "\"\\uDBFF\\uDFFE\"";
        String expected = "\uDBFF\uDFFE";
        parseAndCompareStrings("String10FFFE", input, expected);
    }

    @Test
    public void StringZeroWidthSpace() throws Exception {
        String input = "\"\\u200B\"";
        String expected = "\u200B";
        parseAndCompareStrings("StringZeroWidthSpace", input, expected);
    }

    @Test
    public void StringInvisiblePlus() throws Exception {
        String input = "\"\\u2064\"";
        String expected = "\u2064";
        parseAndCompareStrings("StringInvisiblePlus", input, expected);
    }

    @Test
    public void StringFDD0() throws Exception {
        String input = "\"\\uFDD0\"";
        String expected = "\uFDD0";
        parseAndCompareStrings("StringFDD0", input, expected);
    }

    @Test
    public void StringFFFE() throws Exception {
        String input = "\"\\uFFFE\"";
        String expected = "\uFFFE";
        parseAndCompareStrings("StringFFFE", input, expected);
    }

    @Test
    public void StringUTF8() throws Exception {
        String input = "\"ʬ˥Ω\"";
        String expected = "ʬ˥Ω";
        parseAndCompareStrings("StringUTF8", input, expected);
    }

    @Test
    public void StringEscapedUnicodeQuote() throws Exception {
        String input = "\"\\u0022\"";
        String expected = "\"";
        parseAndCompareStrings("StringEscapedUnicodeQuote", input, expected);
    }

    @Test
    public void StringDelChar() throws Exception {
        String input = "[\"a\u007Fa\"]";
        JSONArray expected = new JSONArray();
        parseAndCompareArray("StringDelChar", input, expected);
    }




        @Test
    public void Null() throws Exception {
        String input = "null";
        parseAndCompareNull("Null", input);
    }

    @Test
    public void False() throws Exception {
        String input = "false";
        Boolean expected = false;
        parseAndCompareBooleans("False", input, expected);
    }

    @Test
    public void True() throws Exception {
        String input = "true";
        Boolean expected = true;
        parseAndCompareBooleans("True", input, expected);
    }





    private void parseAndCompareNull(String message, String input) throws Exception {
        Object result = parse(input);
        assertThat(message, result, is(instanceOf(Null.class)));
    }

    private void parseAndCompareDoubles(String message, String input, Double expected) throws Exception {
        Double result = (Double) parse(input);
        assertThat(message, expected.equals(result), is(true));
    }

    private void parseAndCompareIntegers(String message, String input, Integer expected) throws Exception {
        Integer result = (Integer) parse(input);
        assertThat(message, expected.equals(result), is(true));
    }

    private void parseAndCompareStrings(String message, String input, String expected) throws Exception {
        String result = (String) parse(input);
        assertThat(message, expected.equals(result), is(true));
    }

    private void parseAndCompareBooleans(String message, String input, Boolean expected) throws Exception {
        Boolean result = (Boolean) parse(input);
        assertThat(message, expected.equals(result), is(true));
    }

    private void parseAndCompareObjects(String message, String input, JSONObject expected) throws Exception {
        JSONObject result = (JSONObject) parse(input);
        assertThat(message, expected.equals(result), is(true));
    }

    private void parseAndCompareArray(String message, String input, JSONArray expected) throws Exception {
        JSONArray result = (JSONArray) parse(input);
        assertThat(message, expected.equals(result), is(true));
    }


    // Helpers

    private Object parse(String input) throws Exception {
        createJsonFile(input);
        JsonLexer lexer = new JsonLexer("test.json");
        JsonParser parser = new JsonParser(lexer.extractTokens());
        return parser.parse();
    }

    private void createJsonFile(String inputString) {
        try (PrintWriter pw = new PrintWriter("test.json")) {
            pw.write(inputString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


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