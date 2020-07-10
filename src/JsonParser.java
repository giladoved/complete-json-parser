import java.util.ArrayList;

public class JsonParser {
    private final ArrayList<Token> tokens;
    private int size = 0;

    public JsonParser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public Object parse() throws Exception {
        return parse(0);
    }

    private Object parse(int start) throws Exception {
        Token token = tokens.get(start);
        if (token instanceof True) {
            size = 1;
            return true;
        } else if (token instanceof False) {
            size = 1;
            return false;
        } else if (token instanceof Null) {
            size = 1;
            return new Null();
        } else if (token instanceof StringToken) {
            size = 1;
            return ((StringToken) token).string;
        } else if (token instanceof NumberToken) {
            size = 1;
            String number = ((NumberToken) token).number;
            return Integer.parseInt(number);
        } else if (token instanceof LeftSquareBracket) {
            return parseArray(start + 1);
        } else if (token instanceof LeftCurlyBracket) {
            return parseObject(start + 1);
        }

        return null;
    }

    private JSONObject parseObject(int start) throws Exception {
        JSONObject jsonObject = new JSONObject();
        int end = indexOf(start, false) - 1;
        if (end < 0) throw new Exception(", or } expected");
        int localSize = 2;

        while (start <= end) {
            Token keyToken = tokens.get(start);
            if (!(keyToken instanceof StringToken)) {
                throw new Exception("String key expected");
            }

            Token colonToken = tokens.get(start + 1);
            if (!(colonToken instanceof Colon)) {
                throw new Exception("Colon expected");
            }

            Object parsed = parse(start + 2);
            if (parsed == null) {
                throw new Exception("Invalid token");
            }
            jsonObject.put(((StringToken) keyToken).string, parsed);
            localSize += size + 2;

            if (start + size + 2 >= end) {
                if (tokens.get(start + size + 2) instanceof Comma) {
                    throw new Exception("Trailing comma bro");
                } else {
                    size = localSize;
                    return jsonObject;
                }
            }

            Token afterItem = tokens.get(start + size + 2);
            if (!(afterItem instanceof Comma)) {
                throw new Exception("Put one comma between items");
            }

            start += size + 3;
        }

        size = localSize;
        return jsonObject;
    }

    private JSONArray parseArray(int start) throws Exception {
        JSONArray jsonArray = new JSONArray();
        int end = indexOf(start, true) - 1;
        if (end < 0) throw new Exception(", or ] expected");
        while (start <= end) {
            Object parsed = parse(start);
            if (parsed == null) {
                throw new Exception("Invalid token");
            }
            jsonArray.add(parsed);

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

    private int indexOf(int start, boolean isArray) {
        for (int i = start; i < tokens.size(); i++) {
            if (tokens.get(i) != null) {
                if (isArray) {
                    if (tokens.get(i) instanceof RightSquareBracket) {
                        return i;
                    }
                } else {
                    if (tokens.get(i) instanceof RightCurlyBracket) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }
}
