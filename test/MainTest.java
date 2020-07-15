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



    @Test(expected = ParserException.class)
    public void ArrayItemsWithoutComma() throws Exception {
        String input = "[1 true]";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayInvalidUnicode() throws Exception {
//        String invalidChar = new String(new byte[]{(byte) 0xC0, (byte) 0x80});
//        String input = "\"" + invalidChar + "\"";
        String input = "[�]";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayColonNotComma() throws Exception {
        String input = "[\"\": 1]";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayCommaAfterClose() throws Exception {
        String input = "[\"\"],";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayCommaAndNumber() throws Exception {
        String input = "[,1]";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayDoubleComma() throws Exception {
        String input = "[1,,2]";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayDoubleExtraComma() throws Exception {
        String input = "[\"x\",,]";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayExtraClose() throws Exception {
        String input = "[\"x\"]]";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayExtraComma() throws Exception {
        String input = "[\"\",]";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayIncomplete() throws Exception {
        String input = "[\"x\"";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayIncompleteInvalid() throws Exception {
        String input = "[x";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayInnerNoComma() throws Exception {
        String input = "[3[4]]";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArraySemicolonNotComma() throws Exception {
        String input = "[1;2]";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayJustComma() throws Exception {
        String input = "[,]";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayJustMinus() throws Exception {
        String input = "[-]";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayMissingValue() throws Exception {
        String input = "[   , \"\"]";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayNewlinesUnclosed() throws Exception {
        String input = "[\"a\",\n" +
                "4\n" +
                ",1,";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayNumberAndComma() throws Exception {
        String input = "[1,]";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayNumberAndCommas() throws Exception {
        String input = "[1,,]";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArraySpacesTabFeed() throws Exception {
        String input = "[\"\u000Ba\"\\f]";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayStar() throws Exception {
        String input = "[*]";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayUnclosed() throws Exception {
        String input = "[\"\"";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayUnclosedTrailingComma() throws Exception {
        String input = "[1,";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayUnclosedNewlines() throws Exception {
        String input = "[1,\n" +
                "1\n" +
                ",1";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayUnclosedObject() throws Exception {
        String input = "[{}";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayFalseIncomplete() throws Exception {
        String input = "[fals]";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayNullIncomplete() throws Exception {
        String input = "[nul]";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayTrueIncomplete() throws Exception {
        String input = "[tru]";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void Array1025Brackets() throws Exception {
        String input = "[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayAngleBracket() throws Exception {
        String input = "<.>";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayAngleBracketNull() throws Exception {
        String input = "[<null>]";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayTrailingGarbage() throws Exception {
        String input = "[1]x";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayExtraArrayClose() throws Exception {
        String input = "[1]]";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayUnclosedString() throws Exception {
        String input = "[\"asd]";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayUnopened() throws Exception {
        String input = "1]";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayDouble() throws Exception {
        String input = "[][]";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayClose() throws Exception {
        String input = "]";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayOpen() throws Exception {
        String input = "[";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayNullByteOutsideString() throws Exception {
        String input = "[\u0000]";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayOpenApostrophe() throws Exception {
        String input = "['";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayOpenComma() throws Exception {
        String input = "[,";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayOpenObj() throws Exception {
        String input = "[{";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayOpenObjMultiple() throws Exception {
        String input = "[{\"\":[{\"\":[{\"\":[{\"\":[{\"\":[{\"\":[{\"\":[{\"\":[{\"\":[{\"\":[{\"\":[{\"\":[{\"\":[{\"\":[{";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayOpenQuoteLetter() throws Exception {
        String input = "[\"a";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayOpenString() throws Exception {
        String input = "[\"a\"";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayOpenDigit() throws Exception {
        String input = "[1";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayOpenPartialNull() throws Exception {
        String input = "[ false, nul";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayOpenPartialFalse() throws Exception {
        String input = "[ true, fals";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayOpenPartialTrue() throws Exception {
        String input = "[ false, tru";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayU2060WordJoined() throws Exception {
        String input = "[\u2060]";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void ArrayWhitespaceFormfeed() throws Exception {
        String input = "[\f]";
        parse(input);
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


    @Test(expected = ParserException.class)
    public void NumberPlusPlus() throws Exception {
        String input = "++1234";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberPlusOne() throws Exception {
        String input = "+1";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberPlusInf() throws Exception {
        String input = "+Inf";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberMinus01() throws Exception {
        String input = "-01";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberMinus1Point0Point() throws Exception {
        String input = "-1.0.";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberMinus2Point() throws Exception {
        String input = "-2.";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberMinusNaN() throws Exception {
        String input = "-NaN";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberPointMinus1() throws Exception {
        String input = ".-1";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberPointExp() throws Exception {
        String input = ".2e-3";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void Number0Point1Point2() throws Exception {
        String input = "0.1.2";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberExpPlus() throws Exception {
        String input = "0.3e+";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberNoExp() throws Exception {
        String input = "0.3e";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void Number0PointExp() throws Exception {
        String input = "0.e1";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void Number0ExpUppercasePlus() throws Exception {
        String input = "0E+";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void Number0ExpUppercase() throws Exception {
        String input = "0E";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void Number0ExpPlus() throws Exception {
        String input = "0e+";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void Number0Exp() throws Exception {
        String input = "0e";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void Number1Point0ExpPlus() throws Exception {
        String input = "1.0e+";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void Number1Point0ExpMinus() throws Exception {
        String input = "1.0e-";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void Number1Point0Exp() throws Exception {
        String input = "1.0e";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void Number1000Space() throws Exception {
        String input = "1 000.0";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberDoubleExp() throws Exception {
        String input = "1eE2";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void Number2PointExpPlus() throws Exception {
        String input = "2.e+3";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void Number2PointExpMinus() throws Exception {
        String input = "2.e-3";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void Number2PointExp() throws Exception {
        String input = "2.e3";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void Number9PointExpPlus() throws Exception {
        String input = "9.e+";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberInf() throws Exception {
        String input = "Inf";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberNaN() throws Exception {
        String input = "NaN";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberFullWidthDigitOne() throws Exception {
        String input = "１";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberExpression() throws Exception {
        String input = "1+2";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberHex1Digit() throws Exception {
        String input = "0x1";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberHex2Digits() throws Exception {
        String input = "0x42";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberInfinity() throws Exception {
        String input = "Infinity";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberPlusMinus() throws Exception {
        String input = "0e+-1";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberInvalidNegativeReal() throws Exception {
        String input = "-123.123foo";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberInvalidUnicodeBiggerInt() throws Exception {
        String input = "123�";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberInvalidUnicode1Exp() throws Exception {
        String input = "1e1�";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberInvalidUnicode() throws Exception {
        String input = "0�";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberMinusInfinity() throws Exception {
        String input = "-Infinity";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberNegativeGarbage() throws Exception {
        String input = "-foo";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberMinusSpaceOne() throws Exception {
        String input = "- 1";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberNegativePaddedInt() throws Exception {
        String input = "-012";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberNegativeRealNoInt() throws Exception {
        String input = "-.123";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberNegativeIntGarbage() throws Exception {
        String input = "-1x";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberIntExpGarbage() throws Exception {
        String input = "1ea";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberInvalidUnicodeExp() throws Exception {
        String input = "1e�";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberRealNoFraction() throws Exception {
        String input = "1.";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberDot123() throws Exception {
        String input = ".123";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberAlpha() throws Exception {
        String input = "1.2a-3";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberAlphaChar() throws Exception {
        String input = "1.8011670033376514H-308";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberLeadingZero() throws Exception {
        String input = "012";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberEmpty() throws Exception {
        String input = "";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void NumberTrailingGarbage() throws Exception {
        String input = "2@";
        parse(input);
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
        JSONArray expected = new JSONArray(new ArrayList<>(){{
            add("a\u007Fa");
        }});
        parseAndCompareArray("StringDelChar", input, expected);
    }


    @Test(expected = ParserException.class)
    public void StringSingleSpace() throws Exception {
        String input = " ";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void String1SurrogateThenEscape() throws Exception {
        String input = "\"\\uD800\\\"";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void String1SurrogateThenEscapeU() throws Exception {
        String input = "\"\\uD800\\u\"";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void String1SurrogateThenEscapeU1() throws Exception {
        String input = "\"\\uD800\\u1\"";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void String1SurrogateThenEscapeU1x() throws Exception {
        String input = "\"\\uD800\\u1x\"";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void StringAccentuatedCharNoQuotes() throws Exception {
        String input = "é";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void StringBackslash00() throws Exception {
        String input = "\"\\\"";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void StringEscapeX() throws Exception {
        String input = "\"\\x00\"";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void StringEscapedBackslashBad() throws Exception {
        String input = "\"\\\\\\\"";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void StringEscapedCtrlCharTab() throws Exception {
        String input = "\"\\\t\"";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void StringEscapedEmoji() throws Exception {
        String input = "\"\\\uD83C\uDF00\"";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void StringIncompleteEscape() throws Exception {
        String input = "\"\\\"";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void StringIncompleteEscapedChar() throws Exception {
        String input = "\"\\u00A\"";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void StringIncompleteSurrogate() throws Exception {
        String input = "\"\\uD834\\uDd\"";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void StringIncompleteSurrogateEscapeInvalid() throws Exception {
        String input = "\"\\uD800\\uD800\\x\"";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void StringInvalidUnicodeInEscape() throws Exception {
        String input = "\"\\u�\"";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void StringInvalidBackslashEsc() throws Exception {
        String input = "\"\\a\"";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void StringInvalidUnicodeEsc() throws Exception {
        String input = "\"\\uqqqq\"";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void StringInvalidUnicodeAfterEsc() throws Exception {
        String input = "\"\\�\"";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void StringLeadingUnescapedThinspace() throws Exception {
        String input = "\\u0020\"asd\"";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void StringNoQuotesWithBadEscape() throws Exception {
        String input = "\\n";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void StringSingleDoubleQuote() throws Exception {
        String input = "\"";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void StringSingleQuote() throws Exception {
        String input = "'single quote'";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void StringSingleStringNoDoubleQuotes() throws Exception {
        String input = "abc";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void StringStartEscapeUnclosed() throws Exception {
        String input = "\"\\";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void StringUnescapedCtrlChar() throws Exception {
        String input = "\"aa\"";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void StringUnescapedNewline() throws Exception {
        String input = "\"new\n" +
                "line\"";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void StringUnescapedTab() throws Exception {
        String input = "\"\t\"";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void StringUnicodeCapitalU() throws Exception {
        String input = "\"\\UA66D\"";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void StringTrailingGarbage() throws Exception {
        String input = "\"\"x";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void StringU2060WordJoined() throws Exception {
        String input = "\"\u2060\"";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void StringAsciiId() throws Exception {
        String input = "aå";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void StringIncompleteBOM() throws Exception {
        String input = "�{}";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void StringLoneInvalidUnicode() throws Exception {
        String input = "�";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void StringSingleEacute() throws Exception {
        String input = "�";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void StringSingleStar() throws Exception {
        String input = "*";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void StringUnicodeIdentifier() throws Exception {
        String input = "å";
        parse(input);
    }




    @Test
    public void Null() throws Exception {
        String input = "null";
        parseAndCompareNull("Null", input);
    }

    @Test(expected = ParserException.class)
    public void NullCapitalized() throws Exception {
        String input = "Null";
        parse(input);
    }


    @Test
    public void False() throws Exception {
        String input = "false";
        Boolean expected = false;
        parseAndCompareBooleans("False", input, expected);
    }

    @Test(expected = ParserException.class)
    public void FalseCapitalized() throws Exception {
        String input = "False";
        parse(input);
    }


    @Test
    public void True() throws Exception {
        String input = "true";
        Boolean expected = true;
        parseAndCompareBooleans("True", input, expected);
    }

    @Test(expected = ParserException.class)
    public void TrueCapitalized() throws Exception {
        String input = "True";
        parse(input);
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

    private Object parse(String input) throws ParserException, IOException {
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