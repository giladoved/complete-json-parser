import java.util.LinkedList;

public class JsonParser {
    private final int MAX_DEPTH = 1024;

    private final LinkedList<Token> tokens;
    private int stackDepth = 0;

    public JsonParser(LinkedList<Token> tokens) {
        this.tokens = tokens;
    }

    public Object parse() throws ParserException {
        Object parsed = parseTokens();
        if (parsed == null) {
            throw new ParserException("Invalid Json");
        }
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

        Token token = tokens.pop();
        if (token instanceof True) {
            return true;
        } else if (token instanceof False) {
            return false;
        } else if (token instanceof Null) {
            return new Null();
        } else if (token instanceof StringToken) {
            return ((StringToken) token).string;
        } else if (token instanceof NumberToken) {
            String number = ((NumberToken) token).number;
            try {
                return Integer.parseInt(number);
            } catch (NumberFormatException e) {
            }

            try {
                return Double.parseDouble(number);
            } catch (NumberFormatException e) {
            }

            throw new ParserException("Invalid number");
        } else if (token instanceof LeftSquareBracket) {
            stackDepth++;
            return parseArray();
        } else if (token instanceof LeftCurlyBracket) {
            stackDepth++;
            return parseObject();
        }

        return null;
    }

    private JSONObject parseObject() throws ParserException {
        JSONObject jsonObject = new JSONObject();

        if (tokens.peek() instanceof RightCurlyBracket) {
            tokens.pop();
            return jsonObject;
        }

        while (true) {
            if (tokens.size() == 0) {
                throw new ParserException("Need closing bracket");
            }
            Token keyToken = tokens.pop();
            if (!(keyToken instanceof StringToken)) {
                throw new ParserException("String key expected");
            }

            if (tokens.size() == 0) {
                throw new ParserException("Need closing bracket");
            }
            Token colonToken = tokens.pop();
            if (!(colonToken instanceof Colon)) {
                throw new ParserException("Colon expected");
            }

            Object parsed = parseTokens();
            if (parsed == null) {
                throw new ParserException("Invalid token");
            }
            jsonObject.put(((StringToken) keyToken).string, parsed);

            if (tokens.size() == 0) {
                throw new ParserException("Need closing bracket");
            }
            Token afterItem = tokens.pop();
            if (afterItem instanceof RightCurlyBracket) {
                return jsonObject;
            } else if (!(afterItem instanceof Comma)) {
                throw new ParserException("Comma error");
            }
        }
    }

    private JSONArray parseArray() throws ParserException {
        JSONArray jsonArray = new JSONArray();

        if (tokens.peek() instanceof RightSquareBracket) {
            tokens.pop();
            return jsonArray;
        }

        while (true) {
            Object parsed = parseTokens();
            if (parsed == null) {
                throw new ParserException("Invalid token");
            }
            jsonArray.add(parsed);

            if (tokens.size() == 0) {
                throw new ParserException("Need closing bracket");
            }

            Token afterItem = tokens.pop();
            if (afterItem instanceof RightSquareBracket) {
                return jsonArray;
            } else if (!(afterItem instanceof Comma)) {
                throw new ParserException("Comma error");
            }
        }
    }
}
