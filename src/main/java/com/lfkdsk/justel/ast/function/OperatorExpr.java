/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.ast.function;

import com.lfkdsk.justel.ast.base.AstLeaf;
import com.lfkdsk.justel.ast.base.AstList;
import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.exception.EvalException;
import com.lfkdsk.justel.token.Token;
import com.lfkdsk.justel.utils.GeneratedId;

import java.util.List;

import static com.lfkdsk.justel.compile.generate.Var.getTypeDeclare;
import static com.lfkdsk.justel.utils.TypeUtils.*;

/**
 * Operator Basic Expr
 * eq: factor { OP factor }
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/26.
 */
public abstract class OperatorExpr extends AstList implements Function {

    protected boolean isConstNode = false;

    protected boolean isThisNodeSplite = false;

    public OperatorExpr(List<AstNode> children) {
        super(children, Token.OPERATOR);
    }

    public OperatorExpr(List<AstNode> children, int tag) {
        super(children, tag);
    }

    ///////////////////////////////////////////////////////////////////////////
    // left operator right
    ///////////////////////////////////////////////////////////////////////////

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

    public boolean isConstNode() {
        if (isConstNode) return true;

        Object left = leftChild();
        Object right = rightChild();

        boolean leftConst = false, rightConst = false;

        if (isLiteral(left) && !isIDLiteral(left)) {
            leftConst = true;
        }

        if (isLiteral(right) && !isIDLiteral(right)) {
            rightConst = true;
        }

        if (isOperatorExpr(left)) {
            leftConst = ((OperatorExpr) left).isConstNode();
        }

        if (isOperatorExpr(right)) {
            rightConst = ((OperatorExpr) right).isConstNode();
        }

        return leftConst && rightConst;
    }

    public void checkConstNode() {
        this.isConstNode = isConstNode();
    }

    protected boolean isShouldSplit() {
        return astLevel > 7;
    }

    private String splitSubAst(JustContext env) {
        Object leftValue = leftChild().eval(env);
        Object rightValue = rightChild().eval(env);

        String leftVar = "left" + GeneratedId.generateAtomId();
        String rightVar = "right" + GeneratedId.generateAtomId();

        String leftType = getTypeDeclare(leftValue.getClass());
        String rightType = getTypeDeclare(rightValue.getClass());

        StringBuilder leftStr = new StringBuilder();
        StringBuilder rightStr = new StringBuilder();

        leftStr
                .append(leftType)
                .append(" ")
                .append(leftVar)
                .append("=")
                .append(leftChild().compile(env)).append(";");

        rightStr
                .append(rightType)
                .append(" ")
                .append(rightVar)
                .append("=")
                .append(rightChild().compile(env)).append(";");

//        env.global("static " + leftType + " " + leftVar + ";");
//        env.global("static " + rightType + " " + rightVar + ";");
        env.command(leftStr.toString());
        env.command(rightStr.toString());

        return "(" + leftVar + operator().toString() + rightVar + ")";
    }

    @Override
    public String compile(JustContext env) {
        if (isConstNode) {
            return eval(env).toString();
        } else if (isShouldSplit()) {
            return splitSubAst(env);
        }

        return super.compile(env);
    }
}
