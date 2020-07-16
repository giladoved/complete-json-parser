import org.junit.Test;

public class NullTests extends BaseTestClass {

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
}
