import org.junit.Test;

public class BooleanTests extends BaseTestClass {
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
}
