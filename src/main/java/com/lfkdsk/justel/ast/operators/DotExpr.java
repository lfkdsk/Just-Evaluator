/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.ast.operators;

import com.lfkdsk.justel.ast.base.AstLeaf;
import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.ast.function.OperatorExpr;
import com.lfkdsk.justel.ast.tree.AstPostfixExpr;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.utils.ReflectUtils;
import com.lfkdsk.justel.utils.tools.TextUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Dot Operator
 * eq:
 * 1. primary.field
 * 2. primary.method( expr, expr | null )
 *
 * @author liufengkai
 * Created by liufengkai on 2017/7/26.
 * @see AstPostfixExpr
 * @see OperatorExpr
 */
public class DotExpr extends OperatorExpr implements AstPostfixExpr {

    public DotExpr(List<AstNode> children) {
        super(children, AstNode.DOT_OP);
    }

    public String name() {
        return ((AstLeaf) child(0)).token().getText();
    }

    @Override
    public String funcName() {
        return ".";
    }

    public static class InnerReflect {
        public String name;
        public Object originObj;
        public StringBuilder builder;

        public InnerReflect(String name, Object originObj, StringBuilder builder) {
            this.name = name;
            this.originObj = originObj;
            this.builder = builder;
        }
    }

    @Override
    public Object eval(JustContext env, Object value) {
        // multi obj
        Class<?> cls = value instanceof Class<?> ? (Class<?>) value : value.getClass();

        // get field => field value
        Field field = ReflectUtils.getField(cls, name());
        if (field != null) {
            field.setAccessible(true);
            try {
                return field.get(value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();

                // throw call exception
                return this.eval(env);
            }
        }

        // get method => method value
        return new InnerReflect(name(), value, null);
    }

    @Override
    public Object compile(JustContext env, Object value, StringBuilder builder) {
        Class<?> cls = value instanceof Class<?> ? (Class<?>) value : value.getClass();
        // get field => field value
        Field field = ReflectUtils.getField(cls, name());

        if (field != null && field.isAccessible()) return builder.append(compile(env));

        // method
        String name = name();
        if (!TextUtils.isEmpty(name)) {
            String firstUpper = String.valueOf(name.charAt(0)).toUpperCase() + name.substring(1);
            String getMethodStr = "get" + firstUpper;
            String isMethodStr = "is" + firstUpper;
            Method getMethod = ReflectUtils.getMethod(cls, getMethodStr, new Class[]{});
            Method isMethod = ReflectUtils.getMethod(cls, isMethodStr, new Class[]{});
            try {

                if (getMethod != null) {
                    builder.append(".")
                           .append(getMethodStr)
                           .append("()");

                    // READ: invoke this method to get the value ==> either cannot solve multi-level call.
                    return getMethod.invoke(value);
                }

                if (isMethod != null) {
                    builder.append(".")
                           .append(isMethodStr)
                           .append("()");
                    return isMethod.invoke(value);
                }

            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return builder.append(compile(env));
    }

    @Override
    public String compile(JustContext env) {
        return "." + name();
    }

    @Override
    public boolean isConstNode() {
        return false;
    }

    @Override
    protected boolean isShouldSplit() {
        return false;
    }

    @Override
    public String toString() {

        return "(. " +
                leftChild().toString() +
                ")";
    }
}
