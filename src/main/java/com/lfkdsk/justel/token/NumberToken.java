package com.lfkdsk.justel.token;

import com.lfkdsk.justel.exception.TransferNumberException;

/**
 * Number Token.
 * - Integer
 * - Long
 * - Float
 * - Double
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/24.
 * @see com.lfkdsk.justel.token.Token
 * @see com.lfkdsk.justel.lexer.Lexer
 * @see com.lfkdsk.justel.literal.NumberLiteral
 */
public class NumberToken extends Token {

    /**
     * value => string
     * ep: 1000 => "1000" \ 111.22 => "111.22"
     */
    private String tokenString;

    /**
     * Inner Value
     */
    private Number numberValue;

    /**
     * Number Token
     *
     * @param lineNumber  location
     * @param tag         token.tag
     * @param tokenString value => string
     * @param value       number value
     */
    public NumberToken(int lineNumber, int tag,
                       String tokenString, Number value) {
        super(lineNumber, tag);
        this.tokenString = tokenString;
        this.numberValue = value;
    }

    public long longValue() {
        if (getTag() == Token.LONG) {
            return numberValue.longValue();
        }

        throw new TransferNumberException("wrong value check " +
                "| numberValue's Type isn't long Value " + numberValue.toString());
    }

    public int integerValue() {
        if (getTag() == Token.INTEGER) {
            return numberValue.intValue();
        }

        throw new TransferNumberException("wrong value check " +
                "| numberValue's Type isn't integer Value " + numberValue.toString());
    }

    public float floatValue() {
        if (getTag() == Token.FLOAT) {
            return numberValue.floatValue();
        }

        throw new TransferNumberException("wrong value check " +
                "| numberValue's Type isn't float Value " + numberValue.toString());
    }

    public double doubleValue() {
        if (getTag() == Token.DOUBLE) {
            return numberValue.doubleValue();
        }

        throw new TransferNumberException("wrong value check " +
                "| numberValue's Type isn't double Value " + numberValue.toString());
    }

    public Number getNumberValue() {
        return numberValue;
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
