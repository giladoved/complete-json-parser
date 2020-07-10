import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonLexer {

    //    ^\"([^\"\\\p{Cntrl}])\"
    private final Pattern stringPattern = Pattern.compile("\\\"[^\\\"\\\\]*\\\"");

    //  -?(0|[1-9]\d*)(\.\d+)?([eE][+-]?\d+)?
    private final Pattern numberPattern = Pattern.compile("-?(0|[1-9]\\d*)(\\.\\d+)?([eE][+-]?\\d+)?");

    private String filename;

    public JsonLexer(String filename) {
        this.filename = filename;
    }

    public ArrayList<Token> extractTokens() throws IOException {
        ArrayList<Token> tokens = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filename))))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int index = 0;
                while (index < line.length()) {
                    String subline = line.substring(index);

                    Matcher stringMatcher = stringPattern.matcher(subline);
                    Matcher numberMatcher = numberPattern.matcher(subline);

                    char currentChar = subline.charAt(0);

                    if (subline.contains(new String(True.unicode)) && subline.length() >= True.unicode.length && subline.substring(0, True.unicode.length).equals(new String(True.unicode))) {
                        tokens.add(new True());
                        index += True.unicode.length;
                    } else if (subline.contains(new String(False.unicode)) && subline.length() >= False.unicode.length && subline.substring(0, False.unicode.length).equals(new String(False.unicode))) {
                        tokens.add(new False());
                        index += False.unicode.length;
                    } else if (subline.contains(new String(Null.unicode)) && subline.length() >= Null.unicode.length && subline.substring(0, Null.unicode.length).equals(new String(Null.unicode))) {
                        tokens.add(new Null());
                        index += Null.unicode.length;
                    } else if (numberMatcher.find() && numberMatcher.start() == 0) {
                        tokens.add(new NumberToken(subline.substring(numberMatcher.start(), numberMatcher.end())));
                        index += numberMatcher.end();
                    } else if (stringMatcher.find() && stringMatcher.start() == 0) {
                        tokens.add(new StringToken(subline.substring(stringMatcher.start() + 1, stringMatcher.end() - 1)));
                        index += stringMatcher.end();
                    } else if (currentChar == Colon.unicode) {
                        tokens.add(new Colon());
                        index++;
                    } else if (currentChar == Comma.unicode) {
                        tokens.add(new Comma());
                        index++;
                    } else if (currentChar == LeftCurlyBracket.unicode) {
                        tokens.add(new LeftCurlyBracket());
                        index++;
                    } else if (currentChar == LeftSquareBracket.unicode) {
                        tokens.add(new LeftSquareBracket());
                        index++;
                    } else if (currentChar == RightCurlyBracket.unicode) {
                        tokens.add(new RightCurlyBracket());
                        index++;
                    } else if (currentChar == RightSquareBracket.unicode) {
                        tokens.add(new RightSquareBracket());
                        index++;
                    } else if (currentChar == Tab.unicode) {
//                        tokens.add(new Tab());
                        index++;
                    } else if (currentChar == LineFeed.unicode) {
//                        tokens.add(new LineFeed());
                        index++;
                    } else if (currentChar == CarriageReturn.unicode) {
//                        tokens.add(new CarriageReturn());
                        index++;
                    } else if (currentChar == Space.unicode) {
//                        tokens.add(new Space());
                        index++;
                    } else {
                        index++;
                    }
                }
            }
            return tokens;
        }
    }
}
