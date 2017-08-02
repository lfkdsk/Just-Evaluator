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
import com.lfkdsk.justel.context.JustContext;

import java.util.List;

/**
 * Created by liufengkai on 2017/7/26.
 */
public class AstPrimaryExpr extends AstList {

    public AstPrimaryExpr(List<AstNode> children) {
        super(children, AstNode.PRIMARY_EXPR);
    }

    public static AstNode create(List<AstNode> c) {
        return c.size() == 1 ? c.get(0) : new AstPrimaryExpr(c);
    }

    @Override
    public Object eval(JustContext env) {


        return super.eval(env);
    }
}
