/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.compile.generate;

import com.lfkdsk.justel.utils.ReflectUtils;

/**
 * Var Abstract Class
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/8/4.
 */
public final class Var {

    public final String name;

    private Object value;

    private final Class<?> type;

    public Var(String name, Object value) {
        this.name = name;
        this.value = value;
        this.type = value.getClass();
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public Class<?> getType() {
        return type;
    }

    public String generateVarAssignCode() {
        StringBuilder builder = new StringBuilder();
        Class<?> objType = getType();
        String typeDeclare = objType.getCanonicalName();

        if (ReflectUtils.isPrimitiveOrWrapNumber(objType)) {
            objType = ReflectUtils.toPrimitiveClass(objType);
            typeDeclare = objType.getSimpleName();
        }

        builder.append(typeDeclare).append(" ").append(name).append("=")
                .append("((").append(type.getCanonicalName()).append(")")
                .append("context.get(\"").append(name).append("\")").append(");");

        return builder.toString();
    }
}
