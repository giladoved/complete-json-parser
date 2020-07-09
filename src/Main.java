import java.awt.image.AreaAveragingScaleFilter;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        File baseDir = new File("/Users/giladoved/IdeaProjects/jsonparser/out/production/jsonparser");

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



            ArrayList<JSO> swags = parse(tokens, 0, tokens.size()-1);
            for (JSO jso : swags) {
                System.out.print(jso + " ");
            }
//            System.out.println("SWAG: " + swags);



        } catch (IOException e) {
            System.out.println("e: " + e);
        } catch (Exception e) {
            System.out.println("PARSING ERR: " + e);
            e.printStackTrace();
        }
    }

//    public static LinkedList<Token> tokens = new LinkedList<>();
    public static ArrayList<Token> tokens = new ArrayList<>();
//    public static ArrayList<JSO> parsedTokens = new ArrayList<>();

    private static ArrayList<JSO> parse(ArrayList<Token> tokens, int start, int end) throws Exception {
        ArrayList<JSO> parsedTokens = new ArrayList<>();
        Token token = tokens.get(start);
        if (token instanceof True) {
            parsedTokens.add(new JSO("true"));
            return parsedTokens;
        } else if (token instanceof False) {
            parsedTokens.add(new JSO("false"));
            return parsedTokens;
        } else if (token instanceof Null) {
            parsedTokens.add(new JSO("null"));
            return parsedTokens;
        } else if (token instanceof StringToken) {
            parsedTokens.add(new JSO("String"));
            return parsedTokens;
        } else if (token instanceof NumberToken) {
            parsedTokens.add(new JSO("Number"));
            return parsedTokens;
        }

        else if (token instanceof LeftSquareBracket && tokens.get(end) instanceof RightSquareBracket) {
            parsedTokens.add(new JSO("["));
            parsedTokens.addAll(parseArray(tokens, start+1, end-1));
            parsedTokens.add(new JSO("]"));
            return parsedTokens;
        }

        else if (token instanceof LeftCurlyBracket && tokens.get(end) instanceof RightCurlyBracket) {
            parsedTokens.add(new JSO("{"));
            parsedTokens.addAll(parseObject(tokens, start+1, end-1));
            parsedTokens.add(new JSO("}"));
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

    private static ArrayList<JSO> parseObject(ArrayList<Token> tokens, int start, int end) throws Exception {
        ArrayList<JSO> jsoo = new ArrayList<>();
        JSOO object = new JSOO();

        while (start + 2 <= end) {
            if (!(tokens.get(start) instanceof StringToken)) {
                throw new Exception("NOT A STRING KEY");
            }
            else if (!(tokens.get(start + 1) instanceof Colon)) {
                throw new Exception("NOT A COLON");
            }
            else {
                ArrayList<JSO> jsos = parse(tokens, start+2, end);
                object.map.put(tokens.get(start).toString(), jsos);
                start += jsos.size();
            }
        }

        jsoo.add(object);
        return jsoo;
    }

    private static ArrayList<JSO> parseArray(ArrayList<Token> tokens, int start, int end) throws Exception {
        ArrayList<JSO> array = new ArrayList<>();
        while (start <= end) {
            ArrayList<JSO> jsos = parse(tokens, start, end);
            array.addAll(jsos);

            if (start + jsos.size() > end) {
                return array;
            }

            if (!(tokens.get(start + jsos.size()) instanceof Comma)) {
                throw new Exception("COMMA EXCEPTION");
            }
            else {
                start += jsos.size() + 1;
            }
        }
        return array;
    }

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

class JSO {
    String str = null;
    public JSO(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }
}

class JSOO extends JSO {
    HashMap<String, Object> map;

    public JSOO() {
        super(null);
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
            sb.append(key + ": " + map.get(key) + '\n');
        }
        return sb.toString();
    }
}