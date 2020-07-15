import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        File baseDir = new File("/Users/giladoved/dev/jsonparser/out/production/jsonparser");

        String expected =
                "[\"\\uD801\\udc37\"]\n";
        org.json.JSONArray res = new org.json.JSONArray(expected);
        System.out.println("~~");
        Object swag = res.get(0);
        System.out.println(res.get(0));
        System.out.println("~~");


//        JsonLexer lexer = new JsonLexer(baseDir + "/sample3.json");
//
//        try {
//            LinkedList<Token> tokens = lexer.extractTokens();
//            System.out.println(tokens);
//
//            JsonParser parser = new JsonParser(tokens);
//            Object result = parser.parse();
//            if (result instanceof JSONObject) {
//                System.out.println("object:");
//            } else if (result instanceof JSONArray) {
//                System.out.println("array:");
//            }
//            System.out.println(result);
//        } catch (IOException e) {
//            System.out.println("ERROR FINDING FILE: " + e.getLocalizedMessage());
//        } catch (Exception e) {
//            System.out.println("ERROR PARSING: " + e.getLocalizedMessage());
//            e.printStackTrace();
//        }



    }
}