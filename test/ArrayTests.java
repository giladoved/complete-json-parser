import org.junit.Test;

public class ArrayTests extends BaseTestClass {

    @Test
    public void ArrayWithSpaces() throws Exception {
        String input = "[[]   ]";
        JSONArray expected = new JSONArray() {{
            add(new JSONArray());
        }};
        parseAndCompareArray("ArrayWithSpaces", input, expected);
    }

    @Test
    public void ArrayEmptyString() throws Exception {
        String input = "[\"\"]";
        JSONArray expected = new JSONArray() {{
            add("");
        }};
        parseAndCompareArray("ArrayEmptyString", input, expected);
    }

    @Test
    public void ArrayEmpty() throws Exception {
        String input = "[]";
        JSONArray expected = new JSONArray();
        parseAndCompareArray("ArrayEmpty", input, expected);
    }

    @Test
    public void ArrayEmptySpace() throws Exception {
        String input = "[\n \n \n]";
        JSONArray expected = new JSONArray();
        parseAndCompareArray("ArrayEmptySpace", input, expected);
    }

    @Test
    public void ArrayLeading() throws Exception {
        String input = " []";
        JSONArray expected = new JSONArray();
        parseAndCompareArray("ArrayLeading", input, expected);
    }

    @Test
    public void ArrayTrailing() throws Exception {
        String input = "[] ";
        JSONArray expected = new JSONArray();
        parseAndCompareArray("ArrayTrailing", input, expected);
    }

    @Test
    public void ArrayLeadingAndTrailing() throws Exception {
        String input = " [] ";
        JSONArray expected = new JSONArray();
        parseAndCompareArray("ArrayLeadingAndTrailing", input, expected);
    }

    @Test
    public void ArrayLeadingMultiple() throws Exception {
        String input = "\n  []";
        JSONArray expected = new JSONArray();
        parseAndCompareArray("ArrayLeading", input, expected);
    }

    @Test
    public void ArrayTrailingMultiple() throws Exception {
        String input = "[]  \n";
        JSONArray expected = new JSONArray();
        parseAndCompareArray("ArrayTrailing", input, expected);
    }

    @Test
    public void ArrayLeadingAndTrailingMultiple() throws Exception {
        String input = "\n  []  \n";
        JSONArray expected = new JSONArray();
        parseAndCompareArray("ArrayLeadingAndTrailingMultiple", input, expected);
    }

    @Test
    public void ArrayEndingWithNewline() throws Exception {
        String input = "[\"a\"]\n";
        JSONArray expected = new JSONArray() {{
            add("a");
        }};
        parseAndCompareArray("ArrayEndingWithNewline", input, expected);
    }

    @Test
    public void ArrayFalse() throws Exception {
        String input = "[false]";
        JSONArray expected = new JSONArray() {{
            add(false);
        }};
        parseAndCompareArray("ArrayFalse", input, expected);
    }

    @Test
    public void ArrayTrue() throws Exception {
        String input = "[true]";
        JSONArray expected = new JSONArray() {{
            add(true);
        }};
        parseAndCompareArray("ArrayTrue", input, expected);
    }

    @Test
    public void ArrayNull() throws Exception {
        String input = "[null]";
        JSONArray expected = new JSONArray() {{
            add(null);
        }};
        parseAndCompareArray("ArrayNull", input, expected);
    }

    @Test
    public void ArrayHeterogeneous() throws Exception {
        String input = "[null, 1, \"1\", {}]";
        JSONArray expected = new JSONArray() {{
            add(null);
            add(1);
            add("1");
            add(new JSONObject());
        }};
        parseAndCompareArray("ArrayHeterogeneous", input, expected);
    }

    @Test
    public void ArrayOneWithNewline() throws Exception {
        String input = "[1\n" +
                "]";
        JSONArray expected = new JSONArray() {{
            add(1);
        }};
        parseAndCompareArray("ArrayOneWithNewline", input, expected);
    }

    @Test
    public void ArrayLeadingSpace() throws Exception {
        String input = " [1]";
        JSONArray expected = new JSONArray() {{
            add(1);
        }};
        parseAndCompareArray("ArrayLeadingSpace", input, expected);
    }

    @Test
    public void ArrayManyNulls() throws Exception {
        String input = "[1,null,null,null,2]";
        JSONArray expected = new JSONArray() {{
            add(1);
            add(null);
            add(null);
            add(null);
            add(2);
        }};
        parseAndCompareArray("ArrayManyNulls", input, expected);
    }

    @Test
    public void ArrayTrailingSpace() throws Exception {
        String input = "[1] ";
        JSONArray expected = new JSONArray() {{
            add(1);
        }};
        parseAndCompareArray("ArrayTrailingSpace", input, expected);
    }

    @Test
    public void ArrayString() throws Exception {
        String input = "[\"abc\"]";
        JSONArray expected = new JSONArray() {{
            add("abc");
        }};
        parseAndCompareArray("ArrayString", input, expected);
    }

    @Test
    public void ArrayStringLeadingSpace() throws Exception {
        String input = "[ \"abc\"]";
        JSONArray expected = new JSONArray() {{
            add("abc");
        }};
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
}
