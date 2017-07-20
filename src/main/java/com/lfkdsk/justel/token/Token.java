package com.lfkdsk.justel.token;

/**
 * Created by liufengkai on 2017/7/18.
 */
public class Token {

    public final static int
            AND = 256, BASIC = 257, BREAK = 258, DO = 259, ELSE = 260,
            EQ = 261, FALSE = 262, GE = 263, ID = 264, IF = 265,
            INDEX = 266, LE = 267, MINUS = 268, NE = 269, NUM = 270,
            OR = 271, REAL = 272, TEMP = 273, TRUE = 274, WHILE = 275,
            STRING = 276, LIST = 277, BLOCK = 278, BINARY = 279,
            FUNCTION = 280, NEGATIVE = 281, NULL = 282, PARALIST = 283,
            POSTFIX = 284, PRIMARY = 285, FOR = 286, CLOSURE = 287,
            CLASS_TOKEN = 288, CLASS_BODY_TOKEN = 289, ARRAY = 290,
            CREATE_ARRAY = 291, OPTION = 292, IMPORT = 293, BOOL = 294,
            VAR = 295, INT = 296, FLOAT = 297, TYPE = 298, NEGATIVEBOOL = 295,
            RETURN = 296,
            EOF_TAG = -1, EOL_TAG = -2;

    /**
     * End of file
     */
    public static final Token EOF = new Token(-1, EOF_TAG);

    /**
     * End of line
     */
    public static final String EOL = "\\n";


    private int lineNumber;

    private int tag;

    public Token(int lineNumber, int tag) {
        this.lineNumber = lineNumber;
        this.tag = tag;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public boolean isIdentifier() {
        return false;
    }

    public boolean isNumber() {
        return false;
    }

    public boolean isString() {
        return false;
    }

    public boolean isNull() {
        return false;
    }

    public String getText() {
        return "";
    }

    public int getTag() {
        return tag;
    }

    public boolean isType() {
        return false;
    }

    public boolean isBool() {
        return false;
    }
}
