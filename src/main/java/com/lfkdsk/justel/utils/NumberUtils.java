package com.lfkdsk.justel.utils;

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

    public static boolean isNumber(Object obj) {
        return obj instanceof Number;
    }

    public static boolean isBoolean(Object obj) {
        return obj instanceof Boolean;
    }
}
