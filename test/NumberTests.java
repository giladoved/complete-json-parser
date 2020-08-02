import org.junit.Test;

public class NumberTests extends BaseTestClass {

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
        String input = " 4";
        int expected = 4;
        parseAndCompareIntegers("NumberAfterSpace", input, expected);
    }

    @Test
    public void NumberLeading() throws Exception {
        String input = " 51";
        Integer expected = 51;
        parseAndCompareIntegers("NumberLeading", input, expected);
    }

    @Test
    public void NumberTrailing() throws Exception {
        String input = "51 ";
        Integer expected = 51;
        parseAndCompareIntegers("NumberTrailing", input, expected);
    }

    @Test
    public void NumberLeadingAndTrailing() throws Exception {
        String input = " 51 ";
        Integer expected = 51;
        parseAndCompareIntegers("NumberLeadingAndTrailing", input, expected);
    }

    @Test
    public void NumberLeadingMultiple() throws Exception {
        String input = "\n  51";
        Integer expected = 51;
        parseAndCompareIntegers("NumberLeadingMultiple", input, expected);
    }

    @Test
    public void NumberTrailingMultiple() throws Exception {
        String input = "51 \n";
        Integer expected = 51;
        parseAndCompareIntegers("NumberTrailingMultiple", input, expected);
    }

    @Test
    public void NumberLeadingAndTrailingMultiple() throws Exception {
        String input = "\n  51  \n";
        Integer expected = 51;
        parseAndCompareIntegers("NumberLeadingAndTrailingMultiple", input, expected);
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
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberPlusOne() throws Exception {
        String input = "+1";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberPlusInf() throws Exception {
        String input = "+Inf";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberMinus01() throws Exception {
        String input = "-01";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberMinus1Point0Point() throws Exception {
        String input = "-1.0.";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberMinus2Point() throws Exception {
        String input = "-2.";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberMinusNaN() throws Exception {
        String input = "-NaN";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberPointMinus1() throws Exception {
        String input = ".-1";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberPointExp() throws Exception {
        String input = ".2e-3";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void Number0Point1Point2() throws Exception {
        String input = "0.1.2";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberExpPlus() throws Exception {
        String input = "0.3e+";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberNoExp() throws Exception {
        String input = "0.3e";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void Number0PointExp() throws Exception {
        String input = "0.e1";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void Number0ExpUppercasePlus() throws Exception {
        String input = "0E+";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void Number0ExpUppercase() throws Exception {
        String input = "0E";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void Number0ExpPlus() throws Exception {
        String input = "0e+";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void Number0Exp() throws Exception {
        String input = "0e";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void Number1Point0ExpPlus() throws Exception {
        String input = "1.0e+";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void Number1Point0ExpMinus() throws Exception {
        String input = "1.0e-";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void Number1Point0Exp() throws Exception {
        String input = "1.0e";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void Number1000Space() throws Exception {
        String input = "1 000.0";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberDoubleExp() throws Exception {
        String input = "1eE2";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void Number2PointExpPlus() throws Exception {
        String input = "2.e+3";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void Number2PointExpMinus() throws Exception {
        String input = "2.e-3";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void Number2PointExp() throws Exception {
        String input = "2.e3";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void Number9PointExpPlus() throws Exception {
        String input = "9.e+";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberInf() throws Exception {
        String input = "Inf";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberNaN() throws Exception {
        String input = "NaN";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberFullWidthDigitOne() throws Exception {
        String input = "１";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberExpression() throws Exception {
        String input = "1+2";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberHex1Digit() throws Exception {
        String input = "0x1";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberHex2Digits() throws Exception {
        String input = "0x42";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberInfinity() throws Exception {
        String input = "Infinity";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberPlusMinus() throws Exception {
        String input = "0e+-1";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberInvalidNegativeReal() throws Exception {
        String input = "-123.123foo";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberInvalidUnicodeBiggerInt() throws Exception {
        String input = "123�";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberInvalidUnicode1Exp() throws Exception {
        String input = "1e1�";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberInvalidUnicode() throws Exception {
        String input = "0�";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberMinusInfinity() throws Exception {
        String input = "-Infinity";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberNegativeGarbage() throws Exception {
        String input = "-foo";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberMinusSpaceOne() throws Exception {
        String input = "- 1";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberNegativePaddedInt() throws Exception {
        String input = "-012";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberNegativeRealNoInt() throws Exception {
        String input = "-.123";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberNegativeIntGarbage() throws Exception {
        String input = "-1x";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberIntExpGarbage() throws Exception {
        String input = "1ea";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberInvalidUnicodeExp() throws Exception {
        String input = "1e�";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberRealNoFraction() throws Exception {
        String input = "1.";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberDot123() throws Exception {
        String input = ".123";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberAlpha() throws Exception {
        String input = "1.2a-3";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberAlphaChar() throws Exception {
        String input = "1.8011670033376514H-308";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberLeadingZero() throws Exception {
        String input = "012";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberEmpty() throws Exception {
        String input = "";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NumberTrailingGarbage() throws Exception {
        String input = "2@";
        getParser(input).parse();
    }
}
