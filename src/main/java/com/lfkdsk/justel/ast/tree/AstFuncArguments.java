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

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by liufengkai on 2017/7/26.
 */
public class AstFuncArguments extends AstList implements AstPostfixExpr {
    public AstFuncArguments(List<AstNode> children) {
        super(children, AstNode.FUNC_ARGUMENT_EXPR);
    }


    @Override
    public Object eval(JustContext env, Object value) {
        DotExpr.InnerReflect reflect = (DotExpr.InnerReflect) value;
        int count = this.childCount();

        Object[] newArgs = new Object[count];
        for (int i = 0; i < count; i++) {
            // 对参数表达式进行计算
            newArgs[i] = this.child(i).eval(env);
        }

        try {
            return reflect.method.invoke(reflect.originObj, newArgs);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return this.eval(env);
    }
}
