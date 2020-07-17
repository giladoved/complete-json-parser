import org.junit.Test;

public class BooleanTests extends BaseTestClass {

    @Test
    public void False() throws Exception {
        String input = "false";
        Boolean expected = false;
        parseAndCompareBooleans("lexer.False", input, expected);
    }

    @Test
    public void FalseLeading() throws Exception {
        String input = " false";
        Boolean expected = false;
        parseAndCompareBooleans("lexer.False", input, expected);
    }

    @Test
    public void FalseTrailing() throws Exception {
        String input = "false ";
        Boolean expected = false;
        parseAndCompareBooleans("lexer.False", input, expected);
    }

    @Test
    public void FalseLeadingAndTrailing() throws Exception {
        String input = " false ";
        Boolean expected = false;
        parseAndCompareBooleans("lexer.False", input, expected);
    }

    @Test
    public void FalseLeadingMultiple() throws Exception {
        String input = "\n  false";
        Boolean expected = false;
        parseAndCompareBooleans("lexer.False", input, expected);
    }

    @Test
    public void FalseTrailingMultiple() throws Exception {
        String input = "false  \n";
        Boolean expected = false;
        parseAndCompareBooleans("lexer.False", input, expected);
    }

    @Test
    public void FalseLeadingAndTrailingMultiple() throws Exception {
        String input = "\n  false  \n";
        Boolean expected = false;
        parseAndCompareBooleans("lexer.False", input, expected);
    }

    @Test(expected = ParserException.class)
    public void FalseCapitalized() throws Exception {
        String input = "lexer.False";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void FalseUppercase() throws Exception {
        String input = "FALSE";
        parse(input);
    }



    @Test
    public void True() throws Exception {
        String input = "true";
        Boolean expected = true;
        parseAndCompareBooleans("lexer.True", input, expected);
    }

    @Test
    public void TrueLeading() throws Exception {
        String input = " true";
        Boolean expected = true;
        parseAndCompareBooleans("TrueLeading", input, expected);
    }

    @Test
    public void TrueTrailing() throws Exception {
        String input = "true ";
        Boolean expected = true;
        parseAndCompareBooleans("TrueTrailing", input, expected);
    }

    @Test
    public void TrueLeadingAndTrailing() throws Exception {
        String input = " true ";
        Boolean expected = true;
        parseAndCompareBooleans("TrueLeadingAndTrailing", input, expected);
    }

    @Test
    public void TrueLeadingMultiple() throws Exception {
        String input = "\n true";
        Boolean expected = true;
        parseAndCompareBooleans("TrueLeadingMultiple", input, expected);
    }

    @Test
    public void TrueTrailingMultiple() throws Exception {
        String input = "true \n";
        Boolean expected = true;
        parseAndCompareBooleans("TrueTrailingMultiple", input, expected);
    }

    @Test
    public void TrueLeadingAndTrailingMultiple() throws Exception {
        String input = "\n  true \n";
        Boolean expected = true;
        parseAndCompareBooleans("TrueLeadingAndTrailingMultiple", input, expected);
    }


    @Test(expected = ParserException.class)
    public void TrueCapitalized() throws Exception {
        String input = "lexer.True";
        parse(input);
    }

    @Test(expected = ParserException.class)
    public void TrueUppercase() throws Exception {
        String input = "TRUE";
        parse(input);
    }
}
