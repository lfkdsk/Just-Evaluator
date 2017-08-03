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

import java.lang.reflect.Field;
import java.util.List;

/**
 * primary.field
 * Created by liufengkai on 2017/7/26.
 */
public class DotExpr extends OperatorExpr implements AstPostfixExpr {

    public DotExpr(List<AstNode> children) {
        super(children, AstNode.DOT_OP);
    }

    public String name() {
        return ((AstLeaf) child(0)).token().getText();
    }

    public static class InnerReflect {
        public String name;
        public Object originObj;

        InnerReflect(String name, Object originObj) {
            this.name = name;
            this.originObj = originObj;
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
            }
        }

        // get method => method value
        return new InnerReflect(name(), value);
    }
}
