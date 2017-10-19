/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.ast.operators;

import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.ast.function.OperatorExpr;
import com.lfkdsk.justel.ast.tree.AstPostfixExpr;
import com.lfkdsk.justel.context.JustContext;

import java.lang.reflect.Array;
import java.util.List;

import static com.lfkdsk.justel.utils.TypeUtils.isArray;
import static com.lfkdsk.justel.utils.TypeUtils.isNumber;
import static com.lfkdsk.justel.utils.TypeUtils.isObjectArray;

/**
 * [] Array Index
 * eq: Object[] \ int[] \ float[]
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/26.
 * @see OperatorExpr
 * @see AstPostfixExpr
 */
public class ArrayIndexExpr extends OperatorExpr implements AstPostfixExpr {

    public ArrayIndexExpr(List<AstNode> children) {
        super(children, AstNode.ARRAY_INDEX_OP);
    }

    public AstNode index() {
        return child(0);
    }

    @Override
    public Object eval(JustContext env, Object value) {
        Object index = index().eval(env);
        // index of array
        if (isNumber(index)) {
            if (isObjectArray(value)) {
                // object array
                return ((Object[]) value)[(int) index];
            } else if (isArray(value)) {

                // origin array
                return Array.get(value, (Integer) index);
            }
        }

        return this.eval(env);
    }

    @Override
    public Object compile(JustContext env, Object value, StringBuilder builder) {
        return builder.append(compile(env));
    }

    @Override
    public String postfix() {
        return "[]";
    }

    @Override
    public String compile(JustContext env) {
        return "[" + index().toString() + "]";
    }

    @Override
    public String funcName() {
        return "[]";
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

        return index().toString();
    }
}
