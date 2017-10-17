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
import com.lfkdsk.justel.ast.function.OperatorExpr;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.eval.ConstExpression;

import java.util.List;

import static com.lfkdsk.justel.utils.TypeUtils.isOperatorExpr;

/**
 * Ast Program
 * Program is just a wrapper of Ast.
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/26.
 */
public class AstProgram extends AstList {

    private boolean isProgramConst = false;

    private ConstExpression constExpression = null;

    public AstProgram(List<AstNode> children) {
        super(children, PROGRAM);
    }

    public AstNode program() {
        return child(0);
    }

    public boolean checkConst() {
        AstNode program = program();

        if (isOperatorExpr(program)) {
            this.isProgramConst = ((OperatorExpr) program).isConstNode();
        }

        return isProgramConst;
    }

    @Override
    public Object eval(JustContext env) {
        if (isProgramConst) {
            if (constExpression == null) {
                constExpression = new ConstExpression(program().eval(env));
            }

            return constExpression.eval(env);
        }

        return program().eval(env);
    }

    public boolean isProgramConst() {
        return isProgramConst;
    }

    public ConstExpression getConstExpression() {
        return constExpression;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
