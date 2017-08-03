/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.ast.operators;

import com.lfkdsk.justel.ast.base.AstLeaf;
import com.lfkdsk.justel.ast.base.AstList;
import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.ast.function.Function;
import com.lfkdsk.justel.exception.EvalException;
import com.lfkdsk.justel.token.Token;

import java.util.List;

/**
 * Created by liufengkai on 2017/7/26.
 */
public abstract class OperatorExpr extends AstList implements Function {
    public OperatorExpr(List<AstNode> children) {
        super(children, Token.OPERATOR);
    }

    public OperatorExpr(List<AstNode> children, int tag) {
        super(children, tag);
    }

    protected AstNode leftChild() {
        return child(0);
    }

    protected AstNode rightChild() {
        return child(2);
    }

    protected AstLeaf operator() {
        return (AstLeaf) child(1);
    }

    @Override
    public String functionName() {
        throw new EvalException("Use default eval in operator");
    }
}
