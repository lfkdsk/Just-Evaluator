/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.utils;

import com.lfkdsk.justel.literal.NumberLiteral;

import java.util.function.Function;

/**
 * Created by liufengkai on 2017/7/28.
 */
public class TypeUtils {

    public static boolean isNumber(Object obj) {
        return obj instanceof Number;
    }

    public static boolean isBoolean(Object obj) {
        return obj instanceof Boolean;
    }

    public static boolean isString(Object obj) {
        return obj instanceof String;
    }

    public static boolean isInteger(Object obj) {
        return obj instanceof Integer;
    }

    public static boolean isNumberLiteral(Object obj) {
        return obj instanceof NumberLiteral;
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isComparable(Object obj) {
        return obj instanceof Comparable;
    }

    public static Object checkNull(Object leftObj, Object rightObj,
                                   Function<Object, Object> leftFunc,
                                   Function<Object, Object> rightFunc) {
        if (isNull(leftObj) && isNull(rightObj)) {
            return null;
        } else if (isNull(leftObj)) {
            return leftFunc.apply(leftFunc);
        } else if (isNull(rightObj)) {
            return rightFunc.apply(leftFunc);
        }

        return Boolean.FALSE;
    }
}
