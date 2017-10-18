/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.ast.tree;

import com.lfkdsk.justel.ast.base.AstLeaf;
import com.lfkdsk.justel.ast.base.AstList;
import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.ast.function.Operator;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.exception.CompilerException;
import com.lfkdsk.justel.exception.EvalException;
import com.lfkdsk.justel.parser.BnfCom;

import java.util.List;

/**
 * Ast Binary Expr
 * Binary Expr will be replace by Operator Expr
 *
 * @author liufengkai
 * Created by liufengkai on 2017/7/26.
 * @see com.lfkdsk.justel.parser.JustParserImpl
 * @see com.lfkdsk.justel.ast.function.OperatorExpr
 */
public class AstBinaryExpr extends AstList {

    public AstBinaryExpr(List<AstNode> children) {
        super(children, AstNode.BINARY_EXPR);
    }

    public AstNode leftChild() {
        return child(0);
    }

    public AstNode rightChild() {
        return child(2);
    }

    public AstLeaf midOp() {
        return (AstLeaf) child(1);
    }

    /**
     * Reset AstBinaryExpr to Particular Expr
     * We use BinaryExpr to handle priority of Operator.
     * So we should change it to particular Expr to compute the name.
     *
     * @param expr      Origin AstBinaryExpr
     * @param operators Support Operators
     * @return New Particular Expr
     */
    private AstNode resetAstExpr(AstBinaryExpr expr, BnfCom.Operators operators) {
        // operator is Operator
        Operator operator = (Operator) expr.midOp();
        // get the factory of sub-node
        BnfCom.Factory factory = operators.get(operator.operator()).factory;
        // use list to make new node

        return factory.make(expr.getChildren());
    }

    @Override
    public Object eval(JustContext env) {
        throw new EvalException("can not call basic binary expr node", this);
    }

    @Override
    public String compile(JustContext env) {
        throw new CompilerException("can not compile basic binary expr node", this);
    }
}
