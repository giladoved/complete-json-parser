import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class JSONParser {
    private static final int MAX_DEPTH = 1024;

    private final JSONLexer lexer;
    private LinkedList<Token> tokens;
    private int stackDepth = 0;

    public JSONParser(String jsonStr) {
        lexer = new JSONLexer(jsonStr);
    }

    public JSONParser(File file) {
        lexer = new JSONLexer(file);
    }

    public String parseString() throws ParserException, IOException {
        return (String) parse();
    }

    public Boolean parseBoolean() throws ParserException, IOException {
        return (Boolean) parse();
    }

    public Integer parseInteger() throws ParserException, IOException {
        return (Integer) parse();
    }

    public Double parseDouble() throws ParserException, IOException {
        return (Double) parse();
    }

    public JSONArray parseJSONArray() throws ParserException, IOException {
        return (JSONArray) parse();
    }

    public JSONObject parseJSONObject() throws ParserException, IOException {
        return (JSONObject) parse();
    }

    public Object parse() throws ParserException, IOException {
        this.tokens = lexer.extractTokens();

        Object parsed = parseTokens();
        if (tokens.size() > 0) {
            throw new ParserException("No trailing tokens");
        }

        return parsed;
    }

    private Object parseTokens() throws ParserException {
        if (stackDepth > MAX_DEPTH) {
            throw new ParserException("JSON TOO DEEP");
        }
        if (tokens.size() == 0) {
            return "";
        }

        clearWhitespace();

        Token token;
        if (tokens.size() == 0) {
            throw new ParserException("Empty json");
        } else {
            token = tokens.pop();
        }

        if (token instanceof True) {
            clearWhitespace();
            return true;
        } else if (token instanceof False) {
            clearWhitespace();
            return false;
        } else if (token instanceof Null) {
            clearWhitespace();
            return null;
        } else if (token instanceof StringToken) {
            clearWhitespace();
            return ((StringToken) token).string;
        } else if (token instanceof NumberToken) {
            clearWhitespace();
            String number = ((NumberToken) token).number;
            try {
                return Integer.parseInt(number);
            } catch (NumberFormatException ignored) {
            }

            try {
                return Double.parseDouble(number);
            } catch (NumberFormatException ignored) {
            }

            throw new ParserException("Invalid number");
        } else if (token instanceof LeftSquareBracket) {
            stackDepth++;
            return parseArray();
        } else if (token instanceof LeftCurlyBracket) {
            stackDepth++;
            return parseObject();
        }

        throw new ParserException("Invalid Json");
    }

    private JSONObject parseObject() throws ParserException {
        JSONObject jsonObject = new JSONObject();

        clearWhitespace();
        if (tokens.peek() instanceof RightCurlyBracket) {
            tokens.pop();
            clearWhitespace();
            return jsonObject;
        }

        while (true) {
            if (tokens.size() == 0) {
                throw new ParserException("Need closing bracket");
            }


            clearWhitespace();
            Token keyToken = tokens.pop();
            if (!(keyToken instanceof StringToken)) {
                throw new ParserException("String key expected");
            }
            clearWhitespace();

            if (tokens.size() == 0) {
                throw new ParserException("Need closing bracket");
            }

            Token colonToken = tokens.pop();
            if (!(colonToken instanceof Colon)) {
                throw new ParserException("lexer.Colon expected");
            }

            Object parsed = parseTokens();
            jsonObject.put(((StringToken) keyToken).string, parsed);

            if (tokens.size() == 0) {
                throw new ParserException("Need closing bracket");
            }
            Token afterItem = tokens.pop();
            if (afterItem instanceof RightCurlyBracket) {
                clearWhitespace();
                return jsonObject;
            } else if (!(afterItem instanceof Comma)) {
                throw new ParserException("lexer.Comma error");
            }
        }
    }

    private JSONArray parseArray() throws ParserException {
        JSONArray jsonArray = new JSONArray();

        clearWhitespace();
        if (tokens.peek() instanceof RightSquareBracket) {
            tokens.pop();
            clearWhitespace();
            return jsonArray;
        }

        while (true) {
            Object parsed = parseTokens();
            jsonArray.add(parsed);

            if (tokens.size() == 0) {
                throw new ParserException("Need closing bracket");
            }

            Token afterItem = tokens.pop();
            if (afterItem instanceof RightSquareBracket) {
                clearWhitespace();
                return jsonArray;
            } else if (!(afterItem instanceof Comma)) {
                throw new ParserException("lexer.Comma error");
            }
        }
    }

    private void clearWhitespace() {
        while (tokens.peek() instanceof Whitespace) {
            tokens.pop();
        }
    }
}
