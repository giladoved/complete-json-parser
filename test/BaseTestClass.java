import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BaseTestClass {

    public String resourcesPath = "test/resources";

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    // Helpers

    public void parseAndCompareNull(String message, File file) throws Exception {
        Object result = parse(file);
        assertThat(message, result, is(instanceOf(Null.class)));
    }

    public void parseAndCompareNull(String message, String input) throws Exception {
        Object result = parse(input);
        assertThat(message, result, is(instanceOf(Null.class)));
    }

    public void parseAndCompareDoubles(String message, File file, Double expected) throws Exception {
        Double result = (Double) parse(file);
        assertThat(message, expected.equals(result), is(true));
    }

    public void parseAndCompareDoubles(String message, String input, Double expected) throws Exception {
        Double result = (Double) parse(input);
        assertThat(message, expected.equals(result), is(true));
    }

    public void parseAndCompareIntegers(String message, File file, Integer expected) throws Exception {
        Integer result = (Integer) parse(file);
        assertThat(message, expected.equals(result), is(true));
    }

    public void parseAndCompareIntegers(String message, String input, Integer expected) throws Exception {
        Integer result = (Integer) parse(input);
        assertThat(message, expected.equals(result), is(true));
    }

    public void parseAndCompareStrings(String message, File file, String expected) throws Exception {
        String result = (String) parse(file);
        assertThat(message, expected.equals(result), is(true));
    }

    public void parseAndCompareStrings(String message, String input, String expected) throws Exception {
        String result = (String) parse(input);
        assertThat(message, expected.equals(result), is(true));
    }

    public void parseAndCompareBooleans(String message, File file, Boolean expected) throws Exception {
        Boolean result = (Boolean) parse(file);
        assertThat(message, expected.equals(result), is(true));
    }

    public void parseAndCompareBooleans(String message, String input, Boolean expected) throws Exception {
        Boolean result = (Boolean) parse(input);
        assertThat(message, expected.equals(result), is(true));
    }

    public void parseAndCompareObjects(String message, File file, JSONObject expected) throws Exception {
        JSONObject result = (JSONObject) parse(file);
        assertThat(message, expected.equals(result), is(true));
    }

    public void parseAndCompareObjects(String message, String input, JSONObject expected) throws Exception {
        JSONObject result = (JSONObject) parse(input);
        assertThat(message, expected.equals(result), is(true));
    }

    public void parseAndCompareArray(String message, File file, JSONArray expected) throws Exception {
        JSONArray result = (JSONArray) parse(file);
        assertThat(message, expected.equals(result), is(true));
    }

    public void parseAndCompareArray(String message, String input, JSONArray expected) throws Exception {
        JSONArray result = (JSONArray) parse(input);
        assertThat(message, expected.equals(result), is(true));
    }


    // Helpers

    public Object parse(File file) throws ParserException, IOException {
        String content = Files.readString(Paths.get(file.getAbsolutePath()));
        return parse(content);
    }

    public Object parse(String input) throws ParserException, IOException {
        createJsonFile(input);
        JsonLexer lexer = new JsonLexer("test.json");
        JsonParser parser = new JsonParser(lexer.extractTokens());
        return parser.parse();
    }

    public void createJsonFile(String inputString) {
        try (PrintWriter pw = new PrintWriter("test.json")) {
            pw.write(inputString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
