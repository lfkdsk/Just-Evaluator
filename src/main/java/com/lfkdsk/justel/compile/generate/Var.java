/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.compile.generate;

import com.lfkdsk.justel.utils.ReflectUtils;
import org.jetbrains.annotations.NotNull;

/**
 * Var Abstract Class
 *
 * @author liufengkai
 * Created by liufengkai on 2017/8/4.
 */
public final class Var {

    private String name;

    private Class<?> type;

    private Object value;

    private static Var cache = new Var("object", new Object());

    public Var(@NotNull String name, @NotNull Object value) {
        this.name = name;
        this.value = value;
        this.type = value.getClass();
    }

    public static String getTypeDeclare(Class<?> objType) {
        String typeDeclare = objType.getCanonicalName();

        if (typeDeclare == null) typeDeclare = objType.getName();

        if (ReflectUtils.isPrimitiveOrWrapNumber(objType)) {
            objType = ReflectUtils.toPrimitiveClass(objType);
            typeDeclare = objType.getSimpleName();
        }

        return typeDeclare;
    }

    private void setValue(@NotNull Object o) {
        this.value = o;
        this.type = o.getClass();
    }

    private void setName(@NotNull String name) {
        this.name = name;
    }

    public static Var of(String name, Object obj) {
        cache.setValue(obj);
        cache.setName(name);

        return cache;
    }

    public String getName() {
        return name;
    }

    public Class<?> getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }
}
