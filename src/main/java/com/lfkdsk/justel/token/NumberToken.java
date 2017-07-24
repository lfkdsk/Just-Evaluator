package com.lfkdsk.justel.token;

import com.lfkdsk.justel.exception.ParseException;

/**
 * Created by liufengkai on 2017/7/24.
 */
public class NumberToken extends Token {

    private String tokenString;

    private Number numberValue;

    public NumberToken(int lineNumber, int tag,
                       String tokenString, Number value) {
        super(lineNumber, tag);
        this.tokenString = tokenString;
        this.numberValue = value;
    }

    public long getLongValue() {
        if (getTag() == Token.LONG) {
            return numberValue.longValue();
        }

        throw new ParseException("wrong value check " +
                "| numberValue's Type isn't long Value " + numberValue.intValue());
    }

    public int getIntegerValue() {
        if (getTag() == Token.INTEGER) {
            return numberValue.intValue();
        }

        throw new ParseException("wrong value check " +
                "| numberValue's Type isn't integer Value " + numberValue.longValue());
    }

    @Override
    public boolean isNumber() {
        return true;
    }

    @Override
    public String toString() {
        return "NumberToken{" +
                "tokenString='" + tokenString + '\'' +
                ", numberValue=" + numberValue +
                ", lineNumber=" + lineNumber +
                ", tag=" + tag +
                '}';
    }
}
