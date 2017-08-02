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
import com.lfkdsk.justel.ast.operators.CondOp;
import com.lfkdsk.justel.context.JustContext;

import java.util.List;

import static com.lfkdsk.justel.utils.TypeUtils.isBoolean;

/**
 * Ast Cond Expr
 * conExpr - conOp
 * conExpr - ? trueExpr : falseExpr
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/8/2.
 */
public class AstCondExpr extends AstList {
    public AstCondExpr(List<AstNode> children) {
        super(children, AstNode.COND_EXPR);
    }

    public static boolean isAstCondExpr(AstNode child) {
        return child.childCount() == 2 && child.child(1) instanceof CondOp;
    }

    private AstNode condExpr() {
        return child(0);
    }

    private CondOp condOp() {
        return (CondOp) child(1);
    }

    @Override
    public Object eval(JustContext env) {
        Object boolValue = condExpr().eval(env);
        CondOp condOp = condOp();

        if (isBoolean(boolValue)) {
            condOp.setCond((Boolean) boolValue);
            return condOp.eval(env);
        }

        return super.eval(env);
    }
}
