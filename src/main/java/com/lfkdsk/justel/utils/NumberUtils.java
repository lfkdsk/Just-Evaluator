package com.lfkdsk.justel.utils;

import com.lfkdsk.justel.token.NumberToken;

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

    public static Object computePlusValue(Object l, Object r) {
        return (computeIntValue(l) + computeLongValue(l)
                + computeFloatValue(l) + computeDoubleValue(l))
                + (computeIntValue(r) + computeLongValue(r)
                + computeFloatValue(r) + computeDoubleValue(r));
    }

    public static Object computeValue(Object l) {
        return computeIntValue(l) + computeLongValue(l)
                + computeFloatValue(l) + computeDoubleValue(l);
    }

    public static Object computePlus(NumberToken leftToken, NumberToken rightToken) {
        return (leftToken.integerValue() + leftToken.longValue()
                + leftToken.floatValue() + leftToken.doubleValue())
                + (rightToken.integerValue() + leftToken.longValue()
                + rightToken.floatValue() + rightToken.doubleValue());
    }

    public static Object computeValue(NumberToken token) {
        return token.integerValue() + token.longValue()
                + token.floatValue() + token.doubleValue();
    }
}
