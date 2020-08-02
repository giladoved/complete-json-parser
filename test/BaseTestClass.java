import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class BaseTestClass {

    public String resourcesPath = "test/resources";

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    // Helpers

    public void parseAndCompareNull(String message, File file) throws Exception {
        Object result = getParser(file).parse();
        assertThat(message, result, nullValue());
    }

    public void parseAndCompareNull(String message, String input) throws Exception {
        Object result = getParser(input).parse();
        assertThat(message, result, nullValue());
    }

    public void parseAndCompareDoubles(String message, File file, Double expected) throws Exception {
        Double result = getParser(file).parseDouble();
        assertThat(message, expected.equals(result), is(true));
    }

    public void parseAndCompareDoubles(String message, String input, Double expected) throws Exception {
        Double result = getParser(input).parseDouble();
        assertThat(message, expected.equals(result), is(true));
    }

    public void parseAndCompareIntegers(String message, File file, Integer expected) throws Exception {
        Integer result = getParser(file).parseInteger();
        assertThat(message, expected.equals(result), is(true));
    }

    public void parseAndCompareIntegers(String message, String input, Integer expected) throws Exception {
        Integer result = getParser(input).parseInteger();
        assertThat(message, expected.equals(result), is(true));
    }

    public void parseAndCompareStrings(String message, File file, String expected) throws Exception {
        String result = getParser(file).parseString();
        assertThat(message, expected.equals(result), is(true));
    }

    public void parseAndCompareStrings(String message, String input, String expected) throws Exception {
        String result = getParser(input).parseString();
        assertThat(message, expected.equals(result), is(true));
    }

    public void parseAndCompareBooleans(String message, File file, Boolean expected) throws Exception {
        Boolean result = getParser(file).parseBoolean();
        assertThat(message, expected.equals(result), is(true));
    }

    public void parseAndCompareBooleans(String message, String input, Boolean expected) throws Exception {
        Boolean result = getParser(input).parseBoolean();
        assertThat(message, expected.equals(result), is(true));
    }

    public void parseAndCompareObjects(String message, File file, JSONObject expected) throws Exception {
        JSONObject result = getParser(file).parseJSONObject();
        assertThat(message, expected.equals(result), is(true));
    }

    public void parseAndCompareObjects(String message, String input, JSONObject expected) throws Exception {
        JSONObject result = getParser(input).parseJSONObject();
        assertThat(message, expected.equals(result), is(true));
    }

    public void parseAndCompareArrays(String message, File file, JSONArray expected) throws Exception {
        JSONArray result = getParser(file).parseJSONArray();
        assertThat(message, expected.equals(result), is(true));
    }

    public void parseAndCompareArrays(String message, String input, JSONArray expected) throws Exception {
        JSONArray result = getParser(input).parseJSONArray();
        assertThat(message, expected.equals(result), is(true));
    }

    public JSONParser getParser(File file) {
        return new JSONParser(file);
    }

    public JSONParser getParser(String input) {
        return new JSONParser(input);
    }
}
