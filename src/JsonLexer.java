import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class JSONLexer {

    //    \"([^\"\\\p{Cntrl}]|\\.)*\"
    //    \"([^\"\\]|\\.)*\"
    private final Pattern stringPattern = Pattern.compile("\\\"([^\\\"\\\\]|\\\\.)*\\\"");

    //  -?(0|[1-9]\d*)(\.\d+)?([eE][+-]?\d+)?
    private final Pattern numberPattern = Pattern.compile("-?(0|[1-9]\\d*)(\\.\\d+)?([eE][+-]?\\d+)?");

    private final String filename;

    public JSONLexer(String filename) {
        this.filename = filename;
    }

    public JSONLexer(File file) {
        this.filename = file.getAbsolutePath();
    }

    public LinkedList<Token> extractTokens() throws IOException, ParserException {
        LinkedList<Token> tokens = new LinkedList<>();
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
                        String val = subline.substring(stringMatcher.start() + 1, stringMatcher.end() - 1);
                        if (val.length() == 0) {
                            tokens.add(new StringToken());
                            index += stringMatcher.end();
                        } else if (!isValidUnicode(val)) {
                            throw new ParserException("Illegal unicode");
                        } else if (hasValidEscapes(val)) {
                            String string = convertToEscapedChars(val);
                            tokens.add(new StringToken(string));
                            index += stringMatcher.end();
                        } else {
                            throw new ParserException("Illegal string");
                        }
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
                        tokens.add(new Tab());
                        index++;
                    } else if (currentChar == LineFeed.unicode) {
                        tokens.add(new LineFeed());
                        index++;
                    } else if (currentChar == CarriageReturn.unicode) {
                        tokens.add(new CarriageReturn());
                        index++;
                    } else if (currentChar == Space.unicode) {
                        tokens.add(new Space());
                        index++;
                    } else {
                        throw new ParserException("Illegal token");
                    }
                }
            }

            if (tokens.size() == 0) {
                throw new ParserException("Empty File");
            }
            return tokens;
        }
    }

    private boolean isValidUnicode(String str) {
        return new String(StandardCharsets.UTF_8.encode(str).array(), StandardCharsets.UTF_8).trim().equals(str.trim());
    }

    private String convertToEscapedChars(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c1 = str.charAt(i);
            char c2 = ' ';
            if (i + 1 < str.length()) {
                c2 = str.charAt(i + 1);
            }
            if (c1 == '\\') {
                if (c2 == '\"') {
                    sb.append("\"");
                    i++;
                } else if (c2 == '\\') {
                    sb.append("\\");
                    i++;
                } else if (c2 == '/') {
                    sb.append("/");
                    i++;
                } else if (c2 == 'b') {
                    sb.append('\u0008');
                    i++;
                } else if (c2 == 'f') {
                    sb.append('\u000C');
                    i++;
                } else if (c2 == 'n') {
                    sb.append('\n');
                    i++;
                } else if (c2 == 'r') {
                    sb.append('\r');
                    i++;
                } else if (c2 == 't') {
                    sb.append('\t');
                    i++;
                } else if (c2 == 'u') {
                    String hex = str.substring(i + 2, i + 6);
                    sb.append((char) Integer.parseInt(hex, 16));
                    i += 5;
                }
            } else {
                sb.append(c1);
            }
        }
        return sb.toString();
    }

    private boolean hasValidEscapes(String str) {
        char c1 = 0, c2;
        for (int i = 0; i < str.length() - 1; i++) {
            c1 = str.charAt(i);
            c2 = str.charAt(i + 1);
            if (c1 == '\\') {
                if (c2 == '\\') {
                    i++;
                } else if (c2 == '\"' || c2 == '/' || c2 == 'b' || c2 == 'f' || c2 == 'n' || c2 == 'r' || c2 == 't') {

                } else if (c2 == 'u') {
                    if (i + 6 > str.length()) return false;

                    if (!is4Hex(str.substring(i + 2, i + 6))) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        if (c1 != '\\' && str.charAt(str.length() - 1) == '\\') return false;

        return true;
    }

    private boolean is4Hex(String str) {
        return str.matches("[0-9A-Fa-f]{4}");
    }
}
