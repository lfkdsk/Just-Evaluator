package com.lfkdsk.justel.token;

/**
 * Created by liufengkai on 2017/7/18.
 */
public class Token {

    public final static int
            BASIC = 257, BREAK = 258, DO = 259, ELSE = 260,
            EQ = 261, FALSE = 262, GE = 263, IF = 265,
            INDEX = 266, LE = 267, NE = 269, NUM = 270,
            REAL = 272, TEMP = 273, TRUE = 274, WHILE = 275,
            LIST = 277, BLOCK = 278, BINARY = 279,
            FUNCTION = 280, NEGATIVE = 281, NULL = 282, PARALIST = 283,
            POSTFIX = 284, PRIMARY = 285, FOR = 286, CLOSURE = 287,
            CLASS_TOKEN = 288, CLASS_BODY_TOKEN = 289, ARRAY = 290,
            CREATE_ARRAY = 291, OPTION = 292, IMPORT = 293, BOOL = 294,
            VAR = 295, INT = 296, TYPE = 298, NEGATIVEBOOL = 295,
            RETURN = 296,
            EOF_TAG = -1, EOL_TAG = -2;

    public final static int BOOLEAN = 293;

    public final static int STRING = 294;

    public final static int FLOAT = 295;

    public final static int DOUBLE = 296;

    public final static int INTEGER = 297;

    public final static int LONG = 298;


    /**
     * ID in Language (identity except keyword )
     * ep: lfkdsk ( and -> break is illegal )
     */
    public final static int ID = 299;

    /**
     * Constant / String Literal
     * ep: 12.31 / 1e-10 / "lfkdsk"
     */
    public final static int LITERAL = 300;

    /**
     * Key Word in Language
     * ep: &&
     */
    public final static int KEYWORD = 301;

    /**
     * & Symbol
     */
    public final static int AMPERSAND = 302;

    /**
     * && Symbol
     */
    public final static int AND = 303;

    /**
     * | Symbol
     */
    public final static int BAR = 304;

    /**
     * || Symbol
     */
    public final static int OR = 305;

    /**
     * < Symbol
     */
    public final static int LESS = 306;

    /**
     * <= Symbol
     */
    public final static int LESS_THAN = 307;

    public final static int GREAT = 308;

    public final static int GREAT_THAN = 309;

    /**
     * ? Symbol
     */
    public final static int QUESTION = 310;

    /**
     * : Symbol
     */
    public final static int COLON = 311;
    public final static int PLUS = 312;
    public final static int MINUS = 313;
    public final static int MULTIPLY = 314;
    public final static int DIVIDE = 315;
    public final static int MOD = 316;
    /**
     * !
     */
    public final static int EXCLAM = 317;
    /**
     * !=
     */
    public final static int NOT_EQUAL = 318;

    public final static int DOT = 319;

    public final static int COLLECT_GET_LEFT = 320;

    public final static int COLLECT_GET_RIGHT = 321;

    public final static int RESERVED = 322;

    public final static int EQUAL = 323;

    /**
     * End of file
     */
    public static final Token EOF = new Token(-1, EOF_TAG);

    /**
     * End of line
     */
    public static final String EOL = "\\n";

    protected int lineNumber;

    protected int tag;

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

    @Override
    public String toString() {
        return "Token{" +
                "lineNumber=" + lineNumber +
                ", tag=" + tag +
                '}';
    }
}
