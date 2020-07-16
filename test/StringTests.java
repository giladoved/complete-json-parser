import org.junit.Test;

import java.util.ArrayList;

public class StringTests extends BaseTestClass {
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
        JSONArray expected = new JSONArray(new ArrayList<>() {{
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
}
