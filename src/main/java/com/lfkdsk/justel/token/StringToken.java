package com.lfkdsk.justel.token;

/**
 * Created by liufengkai on 2017/7/24.
 */
public class StringToken extends Token {
    public StringToken(int lineNumber, String text) {
        super(lineNumber, Token.STRING);
    }

    @Override
    public boolean isString() {
        return true;
    }
}
