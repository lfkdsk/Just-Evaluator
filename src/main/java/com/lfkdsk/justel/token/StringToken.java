package com.lfkdsk.justel.token;

/**
 * Created by liufengkai on 2017/7/24.
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
