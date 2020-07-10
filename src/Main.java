import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        File baseDir = new File("/Users/giladoved/dev/jsonparser/out/production/jsonparser");
        JsonLexer lexer = new JsonLexer(baseDir + "/sample3.json");

        try {
            ArrayList<Token> tokens = lexer.extractTokens();
            System.out.println(tokens);

            JsonParser parser = new JsonParser(tokens);
            ArrayList<JSON> swags = parser.parse();
            for (JSON swag : swags) {
                System.out.print(swag + " ");
            }
        } catch (IOException e) {
            System.out.println("ERROR FINDING FILE: " + e.getLocalizedMessage());
        } catch (Exception e) {
            System.out.println("ERROR PARSING: " + e.getLocalizedMessage());
            e.printStackTrace();
        }

    }
}