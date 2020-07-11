import java.util.LinkedList;

public class JsonParser {
    private final LinkedList<Token> tokens;

    public JsonParser(LinkedList<Token> tokens) {
        this.tokens = tokens;
    }

    public Object parse() throws Exception {
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

            throw new Exception("Invalid number");
        } else if (token instanceof LeftSquareBracket) {
            return parseArray();
        } else if (token instanceof LeftCurlyBracket) {
            return parseObject();
        }

        return null;
    }

    private JSONObject parseObject() throws Exception {
        JSONObject jsonObject = new JSONObject();

        while (true) {
            Token keyToken = tokens.pop();
            if (keyToken instanceof RightCurlyBracket) {
                return jsonObject;
            }
            if (!(keyToken instanceof StringToken)) {
                throw new Exception("String key expected");
            }

            Token colonToken = tokens.pop();
            if (!(colonToken instanceof Colon)) {
                throw new Exception("Colon expected");
            }

            Object parsed = parse();
            if (parsed == null) {
                throw new Exception("Invalid token");
            }
            jsonObject.put(((StringToken) keyToken).string, parsed);

            Token afterItem = tokens.pop();
            if (afterItem instanceof RightCurlyBracket) {
                return jsonObject;
            } else if (!(afterItem instanceof Comma)) {
                throw new Exception("Comma error");
            }
        }
    }

    private JSONArray parseArray() throws Exception {
        JSONArray jsonArray = new JSONArray();

        while (true) {
            if (tokens.peek() instanceof RightSquareBracket) {
                tokens.pop();
                return jsonArray;
            }

            Object parsed = parse();
            if (parsed == null) {
                throw new Exception("Invalid token");
            }
            jsonArray.add(parsed);

            Token afterItem = tokens.pop();
            if (afterItem instanceof RightSquareBracket) {
                return jsonArray;
            } else if (!(afterItem instanceof Comma)) {
                throw new Exception("Comma error");
            }
        }
    }
}
