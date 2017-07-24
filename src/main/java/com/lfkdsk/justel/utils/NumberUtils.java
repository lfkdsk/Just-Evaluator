package com.lfkdsk.justel.utils;

/**
 * Created by liufengkai on 2017/7/24.
 */
public class NumberUtils {
    /**
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
     * @param d double number
     */
    public static Number parseNumber(double d) {
        long f = (long) d;
        if (f == d) {
            return parseNumber(f);
        }
        return d;
    }
}
