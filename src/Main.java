import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        File baseDir = new File("/Users/giladoved/dev/jsonparser/out/production/jsonparser");
        JsonLexer lexer = new JsonLexer(baseDir + "/sample3.json");

        try {
            ArrayList<Token> tokens = lexer.extractTokens();
            System.out.println(tokens);

            JsonParser parser = new JsonParser(tokens);
            Object result = parser.parse();
            if (result instanceof JSONObject) {
                System.out.println("object:");
            } else if (result instanceof JSONArray) {
                System.out.println("array:");
            }
            System.out.println(result);
        } catch (IOException e) {
            System.out.println("ERROR FINDING FILE: " + e.getLocalizedMessage());
        } catch (Exception e) {
            System.out.println("ERROR PARSING: " + e.getLocalizedMessage());
            e.printStackTrace();
        }

    }
}