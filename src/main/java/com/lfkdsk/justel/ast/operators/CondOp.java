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
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.token.Token;

import java.util.List;

/**
 * Cond Operator
 * expr ? expr : expr
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/29.
 * @see OperatorExpr
 */
public class CondOp extends OperatorExpr {

    private Boolean isCond = null;

    public CondOp(List<AstNode> children) {
        super(children, Token.COND);
    }

    @Override
    public String funcName() {
        return "?:";
    }

    public void setCond(boolean cond) {
        isCond = cond;
    }

    private AstNode trueExpr() {
        return child(0);
    }

    private AstNode falseExpr() {
        return child(1);
    }

    @Override
    public Object eval(JustContext env) {
        if (isCond != null) {
            if (isCond) {
                return trueExpr().eval(env);
            } else {
                return falseExpr().eval(env);
            }
        }

        return super.eval(env);
    }

    @Override
    public String compile(JustContext env) {
        return "?" + trueExpr().toString() + ":" + falseExpr().toString();
    }

    @Override
    protected boolean isShouldSplit() {
        return false;
    }

    @Override
    public boolean isConstNode() {
        return false;
    }

    @Override
    public String toString() {
        return "(?: " + trueExpr().toString() + " " + falseExpr().toString() + ")";
    }
}
