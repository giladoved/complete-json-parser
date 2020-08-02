import org.junit.Test;

public class NullTests extends BaseTestClass {

    @Test
    public void Null() throws Exception {
        String input = "null";
        parseAndCompareNull("lexer.Null", input);
    }

    @Test
    public void NullTrailing() throws Exception {
        String input = "null ";
        parseAndCompareNull("NullTrailing", input);
    }

    @Test
    public void NullLeading() throws Exception {
        String input = " null";
        parseAndCompareNull("NullLeading", input);
    }

    @Test
    public void NullLeadingAndTrailing() throws Exception {
        String input = " null ";
        parseAndCompareNull("NullLeadingAndTrailing", input);
    }

    @Test
    public void NullTrailingMultiple() throws Exception {
        String input = "null   \n";
        parseAndCompareNull("NullTrailingMultiple", input);
    }

    @Test
    public void NullLeadingMultiple() throws Exception {
        String input = "\n   null";
        parseAndCompareNull("NullLeadingMultiple", input);
    }

    @Test
    public void NullLeadingAndTrailingMultiple() throws Exception {
        String input = "\n   null   \n";
        parseAndCompareNull("NullLeadingAndTrailingMultiple", input);
    }

    @Test(expected = ParserException.class)
    public void NullCapitalized() throws Exception {
        String input = "lexer.Null";
        getParser(input).parse();
    }

    @Test(expected = ParserException.class)
    public void NullUppercase() throws Exception {
        String input = "NULL";
        getParser(input).parse();
    }
}
