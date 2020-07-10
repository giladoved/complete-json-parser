import java.util.ArrayList;

public class JsonParser {
    private final ArrayList<Token> tokens;
    private int size = 0;

    public JsonParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public ArrayList<JSON> parse() throws Exception {
        return parse(this.tokens, 0, this.tokens.size()-1);
    }

    private ArrayList<JSON> parse(ArrayList<Token> tokens, int start, int end) throws Exception {
        ArrayList<JSON> parsedTokens = new ArrayList<>();
        Token token = tokens.get(start);
        if (token instanceof True) {
            size = 1;
            parsedTokens.add(new JSONTrue());
            return parsedTokens;
        } else if (token instanceof False) {
            size = 1;
            parsedTokens.add(new JSONFalse());
            return parsedTokens;
        } else if (token instanceof Null) {
            size = 1;
            parsedTokens.add(new JSONNull());
            return parsedTokens;
        } else if (token instanceof StringToken) {
            size = 1;
            parsedTokens.add(new JSONString(((StringToken) token).string));
            return parsedTokens;
        } else if (token instanceof NumberToken) {
            size = 1;
            parsedTokens.add(new JSONNumber(((NumberToken) token).number));
            return parsedTokens;
        } else if (token instanceof LeftSquareBracket && tokens.get(end) instanceof RightSquareBracket) {
            parsedTokens.add(parseArray(start + 1, end - 1));
            return parsedTokens;
        } else if (token instanceof LeftCurlyBracket && tokens.get(end) instanceof RightCurlyBracket) {
            parsedTokens.add(parseObject(start + 1, end - 1));
            return parsedTokens;
        }

        return null;
    }

    private JSONObject parseObject(int start, int end) throws Exception {
        JSONObject jsonObject = new JSONObject();

//        ArrayList<JSON> keyString = parse(start, end);
//        if (keyString instanceof StringToken) {
//
//        }

        return jsonObject;
    }

    private JSONArray parseArray(int start, int end) throws Exception {
        JSONArray jsonArray = new JSONArray();
        while (start <= end) {
            ArrayList<JSON> item = parse(tokens, start, end);
            if (item == null) {
                throw new Exception("Invalid token");
            }
            jsonArray.addAll(item);

            if (start + size >= end) {
                if (tokens.get(start + size) instanceof Comma) {
                    throw new Exception("Trailing comma bro");
                } else {
                    size = jsonArray.array.size() + 2;
                    return jsonArray;
                }
            }

            Token afterItem = tokens.get(start + size);
            if (!(afterItem instanceof Comma)) {
                throw new Exception("Put one comma between items in array");
            }

            start += size + 1;
        }

        size = jsonArray.array.size() + 2;
        return jsonArray;
    }
}
