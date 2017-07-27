package com.lfkdsk.justel.token;

/**
 * String Token.
 * used in lexer => will be add to spec Literal.
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/24.
 * @see com.lfkdsk.justel.token.Token
 * @see com.lfkdsk.justel.lexer.Lexer
 * @see com.lfkdsk.justel.literal.StringLiteral
 */
public class StringToken extends Token {
    private String text;

    public StringToken(int lineNumber, String text) {
        super(lineNumber, Token.STRING);
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public boolean isString() {
        return true;
    }

    @Override
    public String toString() {
        return "StringToken{" +
                "text='" + text + '\'' +
                ", lineNumber=" + lineNumber +
                ", tag=" + tag +
                '}';
    }
}
