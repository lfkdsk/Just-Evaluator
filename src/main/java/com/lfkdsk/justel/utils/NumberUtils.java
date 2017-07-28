package com.lfkdsk.justel.utils;


import com.lfkdsk.justel.token.NumberToken;
import com.lfkdsk.justel.token.Token;

/**
 * Parser Number.
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/24.
 */
public class NumberUtils {

    /**
     * parser number: int / long
     *
     * @param l long number
     */
    public static Number parseNumber(long l) {
        int i = (int) l;
        if (i == l) {
            return i;
        }
        return l;
    }

    /**
     * parser number: float / double
     *
     * @param d double number
     */
    public static Number parseNumber(double d) {
        long f = (long) d;
        if (f == d) {
            return parseNumber(f);
        }
        return d;
    }

    public static int toInteger(Object obj) {
        return (int) obj;
    }

    public static long toLong(Object obj) {
        return (long) obj;
    }

    public static double toDouble(Number num) {
        if (num instanceof Float) {
            return Double.parseDouble(num.toString());
        }

        return num.doubleValue();
    }


    public static int computeIntValue(Object v) {
        return v instanceof Integer ? (Integer) v : 0;
    }

    public static long computeLongValue(Object v) {
        return v instanceof Long ? (long) v : 0;
    }

    public static float computeFloatValue(Object v) {
        return v instanceof Float ? (Float) v : (int) 0;
    }

    public static double computeDoubleValue(Object v) {
        return v instanceof Double ? (double) v : 0;
    }

    public static double computePlusValue(Object l, Object r) {
        return computeValue(l) + computeValue(r);
    }

    public static double computeValue(Object l) {
        return computeIntValue(l) + computeLongValue(l)
                + computeFloatValue(l) + computeDoubleValue(l);
    }

    public static Object computePlusToken(NumberToken leftToken, NumberToken rightToken) {
        return castTokenValue(computeValueToken(leftToken) + computeValueToken(rightToken),
                Math.max(leftToken.getTag(), rightToken.getTag()));
    }

    public static Object computeNegative(Number l) {
        return castTokenValue(-computeValue(l), numberValue(l));
    }

    public static Object computeNegativeToken(NumberToken token) {
        return castTokenValue(-computeValueToken(token), token.getTag());
    }

    public static double computeValueToken(NumberToken token) {
        return (token.integerValue() + token.longValue()
                + token.floatValue() + token.doubleValue());
    }

    public static Number castTokenValue(Number number, final int flag) {
        switch (flag) {
            case Token.INTEGER:
                return number.intValue();
            case Token.LONG:
                return number.longValue();
            case Token.FLOAT:
                return number.floatValue();
            case Token.DOUBLE:
            default:
                return number.doubleValue();
        }
    }

    public static Number castTokenValue(NumberToken token) {
        switch (token.getTag()) {
            case Token.INTEGER:
                return token.integerValue();
            case Token.LONG:
                return token.longValue();
            case Token.FLOAT:
                return token.floatValue();
            case Token.DOUBLE:
            default:
                return token.doubleValue();
        }
    }

    public static <T extends Number> int numberValue(T obj) {
        if (obj instanceof Integer) {
            return Token.INTEGER;
        } else if (obj instanceof Long) {
            return Token.LONG;
        } else if (obj instanceof Float) {
            return Token.FLOAT;
        } else {
            return Token.DOUBLE;
        }
    }

    public static Object computeAmpersandValue(Number num1, Number num2) {
        return castTokenValue((long) computeValue(num1) & (long) computeValue(num2),
                Math.min(numberValue(num1), numberValue(num2)));
    }
}
