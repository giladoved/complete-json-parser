abstract class Token {
}

class LeftSquareBracket extends Token {
    static char unicode = '\u005B';

    @Override
    public String toString() {
        return "LeftSquareBracket";
    }
}

class LeftCurlyBracket extends Token {
    static char unicode = '\u007B';

    @Override
    public String toString() {
        return "LeftCurlyBracket";
    }
}

class RightSquareBracket extends Token {
    static char unicode = '\u005D';

    @Override
    public String toString() {
        return "RightSquareBracket";
    }
}

class RightCurlyBracket extends Token {
    static char unicode = '\u007D';

    @Override
    public String toString() {
        return "RightCurlyBracket";
    }
}

class Colon extends Token {
    static char unicode = '\u003A';

    @Override
    public String toString() {
        return "COLON";
    }
}

class Comma extends Token {
    static char unicode = '\u002C';

    @Override
    public String toString() {
        return "COMMA";
    }
}

class True extends Token {
    static char[] unicode = {'\u0074', '\u0072', '\u0075', '\u0065'};

    @Override
    public String toString() {
        return "TRUE";
    }
}

class False extends Token {
    static char[] unicode = {'\u0066', '\u0061', '\u006C', '\u0073', '\u0065'};

    @Override
    public String toString() {
        return "FALSE";
    }
}

class Null extends Token {
    static char[] unicode = {'\u006E', '\u0075', '\u006C', '\u006C'};

    @Override
    public String toString() {
        return "NULL";
    }
}

class Whitespace extends Token {

}

class Tab extends Whitespace {
    static char unicode = '\u0009';

    @Override
    public String toString() {
        return "Tab";
    }
}

class LineFeed extends Whitespace {
    static char unicode = '\n';

    @Override
    public String toString() {
        return "LineFeed";
    }
}

class CarriageReturn extends Whitespace {
    static char unicode = '\r';

    @Override
    public String toString() {
        return "CarriageReturn";
    }
}

class Space extends Whitespace {
    static char unicode = '\u0020';

    @Override
    public String toString() {
        return "Space";
    }
}


class NumberToken extends Token {
    String number;
    public NumberToken(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "NumberToken(" + number + ")";
    }
}

class StringToken extends Token {
    String string;
    public StringToken(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return "StringToken(" + string + ")";
    }
}