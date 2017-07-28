/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.utils;

import com.lfkdsk.justel.literal.NumberLiteral;

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


}
