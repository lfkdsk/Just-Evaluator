package com.lfkdsk.justel.token;

/**
 * Token the lexer will skip & reuse.
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/24.
 * @see com.lfkdsk.justel.token.Token
 * @see com.lfkdsk.justel.lexer.JustLexerImpl
 * @see com.lfkdsk.justel.parser.JustParserImpl
 */
public class SepToken extends Token {

    public static SepToken AMPERSAND_TOKEN = new SepToken(Token.AMPERSAND, "&");
    public static SepToken AND_TOKEN = new SepToken(Token.AND, "&&");

    public static SepToken BAR_TOKEN = new SepToken(Token.BAR, "|");
    public static SepToken OR_TOKEN = new SepToken(Token.OR, "||");

    public static SepToken LESS_TOKEN = new SepToken(Token.LESS, "<");
    public static SepToken LTE_TOKEN = new SepToken(Token.LESS_THAN, "<=");

    public static SepToken GREAT_TOKEN = new SepToken(Token.GREAT, ">");
    public static SepToken GTE_TOKEN = new SepToken(Token.GREAT_THAN, ">=");

    public static SepToken QUESTION_TOKEN = new SepToken(Token.QUESTION, "?");
    public static SepToken COLON_TOKEN = new SepToken(Token.COLON, ":");

    public static SepToken PLUS_TOKEN = new SepToken(Token.PLUS, "+");
    public static SepToken MINUS_TOKEN = new SepToken(Token.MINUS, "-");
    public static SepToken MULTIPLY_TOKEN = new SepToken(Token.MULTIPLY, "*");
    public static SepToken DIVIDE_TOKEN = new SepToken(Token.DIVIDE, "/");

    public static SepToken MOD_TOKEN = new SepToken(Token.MOD, "%");

    public static SepToken EXCLAM_TOKEN = new SepToken(Token.EXCLAM, "!");
    public static SepToken NOT_EQUAL_TOKEN = new SepToken(Token.NOT_EQUAL, "!=");
    public static SepToken EQUAL_TOKEN = new SepToken(Token.EQUAL, "==");

    public static SepToken DOT_TOKEN = new SepToken(Token.DOT, ".");
    public static SepToken COLLECT_GET_LEFT_TOKEN = new SepToken(Token.COLLECT_GET_LEFT, "[");
    public static SepToken COLLECT_GET_RIGHT_TOKEN = new SepToken(Token.COLLECT_GET_RIGHT, "]");

    public static SepToken EOL_TOKEN = new SepToken(Token.EOL_TAG, Token.EOL);

    /**
     * Skip text.
     */
    private String text;

    public SepToken(int tag, String text) {
        this(-1, tag, text);
    }

    public SepToken(int lineNumber, int tag, String text) {
        super(lineNumber, tag);
        this.text = text;

        if (text.equals(Token.EOL)) {
            this.tag = Token.EOL_TAG;
        }
    }

    @Override
    public boolean isIdentifier() {
        return true;
    }

    @Override
    public int getTag() {
        return super.getTag();
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "SepToken{" +
                "text='" + text + '\'' +
                ", lineNumber=" + lineNumber +
                ", tag=" + tag +
                '}';
    }
}
