import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        File baseDir = new File("/Users/giladoved/dev/jsonparser/out/production/jsonparser");

//        LinkedList<Token> tokens = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(baseDir, "sample3.json"))))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Scanner scanner = new Scanner(line);
//                System.out.println("line: " + line);

                int index = 0;
                while (index < line.length()) {
                    String subline = line.substring(index);

                    Matcher stringMatcher = Pattern.compile("\\\"[^\\\"\\\\]*\\\"").matcher(subline);
                    Matcher numberMatcher = Pattern.compile("-?(0|[1-9]\\d*)(\\.\\d+)?([eE][+-]?\\d+)?").matcher(subline);

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
                        tokens.add(new StringToken(subline.substring(stringMatcher.start(), stringMatcher.end())));
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
//                    if (token.matches("-?(0|[1-9]\\d*)(\\.\\d+)?([eE][+-]?\\d+)?")) { //  -?(0|[1-9]\d*)(\.\d+)?([eE][+-]?\d+)?
//                        tokens.add(new NumberToken(token));
//                    } else if (token.matches("^\\\"([^\\\"\\\\\\p{Cntrl}])\\\"")) { //    ^\"([^\"\\\p{Cntrl}])\"
//                        tokens.add(new StringToken(token));
            }
            System.out.println(tokens);


            ArrayList<JSON> swags = parse(tokens, 0, tokens.size() - 1);
            for (JSON swag : swags) {
                System.out.print(swag + " ");
            }


        } catch (IOException e) {
            System.out.println("e: " + e);
        } catch (Exception e) {
            System.out.println("PARSING ERR: " + e);
            e.printStackTrace();
        }
    }

    public static int size = 0;
    //    public static LinkedList<Token> tokens = new LinkedList<>();
    public static ArrayList<Token> tokens = new ArrayList<>();
//    public static ArrayList<JSO> parsedTokens = new ArrayList<>();

    private static ArrayList<JSON> parse(ArrayList<Token> tokens, int start, int end) throws Exception {
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
            parsedTokens.add(parseArray(tokens, start + 1, end - 1));
            return parsedTokens;
        } else if (token instanceof LeftCurlyBracket && tokens.get(end) instanceof RightCurlyBracket) {
            parsedTokens.add(parseObject(tokens, start + 1, end - 1));
            return parsedTokens;
        }

//        else if (token instanceof LeftSquareBracket && tokens.get(end) instanceof RightSquareBracket) {
//            return isValidArray(tokens, start+1, end-1);
//        } else if (token instanceof LeftCurlyBracket && tokens.get(end) instanceof RightCurlyBracket) {
//            return isValidObject(tokens, start+1, end-1);
//        }

//        return false;

        return null;
    }

    private static JSONArray parseArray(ArrayList<Token> tokens, int start, int end) throws Exception {
        JSONArray jsonArray = new JSONArray();
        while (start <= end) {
            ArrayList<JSON> item = parse(tokens, start, end);
            if (item != null) {
                jsonArray.addAll(item);
            }

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

    private static JSONObject parseObject(ArrayList<Token> tokens, int start, int end) throws Exception {
        JSONObject jsonObject = new JSONObject();

        return jsonObject;
    }



//    private static JSONObject parseObject(ArrayList<Token> tokens, int start, int end) throws Exception {
//        JSONObject jsonObject = new JSONObject();
//        int s = start;
//
//        while (start + 2 <= end) {
//            if (!(tokens.get(start) instanceof StringToken)) {
//                throw new Exception("NOT A STRING KEY");
//            } else if (!(tokens.get(start + 1) instanceof Colon)) {
//                throw new Exception("NOT A COLON");
//            } else {
//                ArrayList<JSON> jsos = parse(tokens, start + 2, end);
//                jsonObject.put(tokens.get(start).toString(), jsos);
//
//                if (start + 2 + size >= end) {
//                    if ((tokens.get(start + 2 + size) instanceof Comma)) {
//                        throw new Exception("Trailing comma bro");
//                    }
//
//                    size = end - start;
//                    return jsonObject;
//                } else if (!(tokens.get(start + 2 + size) instanceof Comma)) {
//                    throw new Exception("Comma exception");
//                } else {
//                    start += size + 3;
//                }
//            }
//        }
//
//        size = start - s;
//        return jsonObject;
//    }

//    private static JSONArray parseArray(ArrayList<Token> tokens, int start, int end) throws Exception {
//        JSONArray jsonArray = new JSONArray();
//        int s = start;
//
//        while (start <= end) {
//            ArrayList<JSON> jsos = parse(tokens, start, end);
//            if (jsos != null) {
//                jsonArray.addAll(jsos);
//            }
//
//            if (start + size > end) {
//                if (start == end) {
//                    size = end - start;
//                    return jsonArray;
//                }
//                else if (!(tokens.get(start + size) instanceof Comma)) {
//                    throw new Exception("Trailing Comma bro");
//                }
//            }
//            else if (!(tokens.get(start + size) instanceof Comma)) {
//                throw new Exception("Need one comma between items");
//            } else {
//                start += size + 1;
//            }
//        }
//
//        size = start - s;
//        return jsonArray;
//    }

//    private static boolean isValid(LinkedList<Token> tokens) {
//        Token token = tokens.pop();
//        if (token instanceof True) {
//            return true;
//        } else if (token instanceof False) {
//            return true;
//        } else if (token instanceof Null) {
//            return true;
//        } else if (token instanceof StringToken) {
//            return true;
//        } else if (token instanceof NumberToken) {
//            return true;
//        }
//        else if (token instanceof LeftSquareBracket) {
//            return isValidArray(tokens);
//        }
////        else if (token instanceof LeftSquareBracket && tokens.get(end) instanceof RightSquareBracket) {
////            return isValidArray(tokens, start+1, end-1);
////        } else if (token instanceof LeftCurlyBracket && tokens.get(end) instanceof RightCurlyBracket) {
////            return isValidObject(tokens, start+1, end-1);
////        }
//
//        return false;
//    }
//
//    private static boolean isValidArray(LinkedList<Token> tokens) {
//        if (tokens.getFirst() == null) return false;
//
//        Token token = tokens.getFirst();
//        if (token instanceof RightSquareBracket) {
//            tokens.pop();
//            return true;
//        }
//
//        while (true) {
//            boolean isTrue = isValid(tokens);
//            if (!isTrue) {
//                return false;
//            }
//            else if (tokens.getFirst() instanceof RightSquareBracket) {
//                tokens.pop();
//                return true;
//            }
//            else if (!(tokens.getFirst() instanceof Comma)) {
//                return false;
//            }
//            else {
//                tokens.pop();
//            }
//        }
//    }

//    private static boolean isValidObject(ArrayList<Token> tokens, int start, int end) {
//        if (start == end) return true;
//
//        if (tokens.get(start) instanceof StringToken && tokens.get(start+1) instanceof Colon) {
//            return isValid(tokens, start+2, end);
//        }
//
//        return false;
//    }
}


class JSON {

}

class JSONString extends JSON {
    String string;

    public JSONString(String str) {
        this.string = str;
    }

    @Override
    public String toString() {
        return string;
    }
}

class JSONNumber extends JSON {
    int number;

    public JSONNumber(String numStr) {
        this.number = Integer.parseInt(numStr);
    }

    @Override
    public String toString() {
        return number + "";
    }
}

class JSONTrue extends JSON {
    @Override
    public String toString() {
        return "true";
    }
}

class JSONFalse extends JSON {
    @Override
    public String toString() {
        return "false";
    }
}

class JSONNull extends JSON {
    @Override
    public String toString() {
        return "null";
    }
}

class JSONArray extends JSON {
    ArrayList<Object> array;

    public JSONArray() {
        array = new ArrayList<>();
    }

    public void addAll(ArrayList<JSON> items) {
        array.addAll(items);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < array.size(); i++) {
            sb.append(array.get(i));
            if (i != array.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}

class JSONObject extends JSON {
    HashMap<String, Object> map;

    public JSONObject() {
        map = new HashMap<>();
    }

    public void put(String key, Object val) throws Exception {
        if (map.containsKey(key)) {
            throw new Exception("Duplicate key error");
        }

        map.put(key, val);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('\n');
        for (String key : map.keySet()) {
            sb.append(key)
                    .append(": ")
                    .append(map.get(key))
                    .append('\n');
        }
        return sb.toString();
    }
}