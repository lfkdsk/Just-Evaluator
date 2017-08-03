/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.ast.tree;

import com.lfkdsk.justel.ast.base.AstList;
import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.ast.operators.DotExpr;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.utils.ReflectUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Func Arguments
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/26.
 */
public class AstFuncArguments extends AstList implements AstPostfixExpr {

    public AstFuncArguments(List<AstNode> children) {
        super(children, AstNode.FUNC_ARGUMENT_EXPR);
    }

    @Override
    public Object eval(JustContext env, Object value) {

        DotExpr.InnerReflect reflect = (DotExpr.InnerReflect) value;
        // count of children
        int count = this.childCount();
        // cls obj
        Class<?> cls = ((DotExpr.InnerReflect) value).originObj.getClass();

        // compute new args
        Object[] newArgs = new Object[count];
        for (int i = 0; i < count; i++) {
            newArgs[i] = this.child(i).eval(env);
        }

        // class type to get-method
        Class<?>[] args = new Class[newArgs.length];
        for (int i = 0; i < newArgs.length; i++) {
            args[i] = newArgs[i].getClass();
        }

        Method method = ReflectUtils.getMethod(cls, reflect.name, args);

        if (method != null) {
            method.setAccessible(true);

            try {
                return method.invoke(reflect.originObj, newArgs);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return this.eval(env);
    }
}
