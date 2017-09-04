/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.utils;

import com.lfkdsk.justel.ast.function.OperatorExpr;
import com.lfkdsk.justel.literal.IDLiteral;
import com.lfkdsk.justel.literal.Literal;
import com.lfkdsk.justel.literal.NumberLiteral;
import com.lfkdsk.justel.literal.StringLiteral;

import java.util.List;

/**
 * Type Utils
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/28.
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

    public static boolean isObjectArray(Object obj) {
        return obj instanceof Object[];
    }

    public static boolean isList(Object obj) {
        return obj instanceof List<?>;
    }

    public static boolean isArray(Object obj) {
        return obj.getClass().isArray();
    }

    public static boolean isLiteral(Object obj) {
        return obj instanceof Literal;
    }

    public static boolean isNumberLiteral(Object obj) {
        return obj instanceof NumberLiteral;
    }

    public static boolean isStringLiteral(Object obj) {
        return obj instanceof StringLiteral;
    }

    public static boolean isIDLiteral(Object obj) {
        return obj instanceof IDLiteral;
    }

    public static boolean isOperatorExpr(Object obj) {
        return obj instanceof OperatorExpr;
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isComparable(Object obj) {
        return obj instanceof Comparable;
    }
}
