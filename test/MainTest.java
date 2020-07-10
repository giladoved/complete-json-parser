import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.PrintWriter;
import java.util.ArrayList;

public class MainTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void ArrayEmpty() throws Exception {
        String input = "[]";
        ArrayList<Object> expected = new ArrayList<>();
        assert parseAndCompareArray(input, expected);
    }

    @Test
    public void ArrayOne() throws Exception {
        String input = "[1]";
        ArrayList<Object> expected = new ArrayList<>() {{
            add(new JSONNumber(1));
        }};
        assert parseAndCompareArray(input, expected);
    }

    @Test
    public void ArrayTwo() throws Exception {
        String input = "[1, 2]";
        ArrayList<Object> expected = new ArrayList<>() {{
            add(new JSONNumber(1));
            add(new JSONNumber(2));
        }};
        assert parseAndCompareArray(input, expected);
    }

    @Test(expected = Exception.class)
    public void ArrayOneComma() throws Exception {
        String input = "[,]";
        ArrayList<Object> expected = new ArrayList<>();
        assert parseAndCompareArray(input, expected);
    }

    @Test(expected = Exception.class)
    public void ArrayDoubleComma() throws Exception {
        String input = "[1,, 2]";
        ArrayList<Object> expected = new ArrayList<>() {{
            add(new JSONNumber(1));
            add(new JSONNumber(2));
        }};
        assert parseAndCompareArray(input, expected);
    }

    @Test(expected = Exception.class)
    public void ArrayTrailingComma() throws Exception {
        String input = "[1,2,]";
        ArrayList<Object> expected = new ArrayList<>() {{
            add(new JSONNumber(1));
            add(new JSONNumber(2));
        }};
        assert parseAndCompareArray(input, expected);
    }

    @Test()
    public void ArrayOneNested() throws Exception {
        String input = "[1,[]]";
        ArrayList<Object> expected = new ArrayList<>() {{
            add(new JSONNumber(1));
            add(new JSONArray());
        }};
        assert parseAndCompareArray(input, expected);
    }


    @Test
    public void test1() throws Exception {
//        String input = "{\"name\":\"g\"}";
        String input = "[1, 2, 3]";
        ArrayList<Object> expected = new ArrayList<>() {{
            add(new JSONNumber(1));
            add(new JSONNumber(2));
            add(new JSONNumber(3));
        }};
        assert parseAndCompareArray(input, expected);
    }

    private boolean parseAndCompareArray(String input, ArrayList<Object> expected) throws Exception {
        createJsonFile(input);
        JsonLexer lexer = new JsonLexer("test.json");
        JsonParser parser = new JsonParser(lexer.extractTokens());
        ArrayList<JSON> jsons = parser.parse();
        return areArraysEqual(expected, ((JSONArray) jsons.get(0)).array);
    }

    private boolean areArraysEqual(ArrayList<Object> expected, ArrayList<Object> actual) {
        if (expected.size() != actual.size()) return false;

        for (int i = 0; i < expected.size(); i++) {
            if (!expected.get(i).equals(actual.get(i))) {
                return false;
            }
        }

        return true;
    }

    private void createJsonFile(String inputString) {
        try (PrintWriter pw = new PrintWriter("test.json")) {
            pw.write(inputString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}