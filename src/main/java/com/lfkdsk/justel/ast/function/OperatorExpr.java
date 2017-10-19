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

import static com.lfkdsk.justel.generate.javagen.Var.getTypeDeclare;
import static com.lfkdsk.justel.utils.TypeUtils.*;

/**
 * Operator Basic Expr
 * eq: factor { OP factor }
 *
 * @author liufengkai
 * Created by liufengkai on 2017/7/26.
 */
public abstract class OperatorExpr extends AstList implements Function {

    protected boolean isConstNode = false;

    protected boolean isThisNodeSpited = false;

    public OperatorExpr(List<AstNode> children) {
        super(children, Token.OPERATOR);
        this.checkConstNode();
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
    public String funcName() {
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

        return isConstNode = (leftConst && rightConst);
    }

    public void checkConstNode() {
        this.isConstNode = isConstNode();
    }

    protected boolean isShouldSplit() {
        return astLevel > 4;
    }

    private String splitSubAstEval(JustContext env) {
        // reset this state flag
        this.isThisNodeSpited = true;

        AstNode left = leftChild();
        AstNode right = rightChild();

        String leftStr = left.toString();
        String rightStr = right.toString();

        String leftVar, rightVar;

        if (leftStr.length() < rightStr.length()) {
            leftVar = attachCache(left, env);
            rightVar = attachCache(right, env);
        } else {
            rightVar = attachCache(right, env);
            leftVar = attachCache(left, env);
        }

        return "(" + leftVar + operator().toString() + rightVar + ")";
    }

    private String attachCache(AstNode node, JustContext env) {
        Object leftCache = env.getCache(node.hashCode());

        String var;

        if (leftCache == null) {
            var = "var" + GeneratedId.generateAtomId();
            env.putCache(node.hashCode(), var);
            env.command(generateNewVar(var, node, env));
        } else {
            var = (String) leftCache;
        }

        return var;
    }

    private String generateNewVar(String var, AstNode node, JustContext env) {
        Object value = node.eval(env);
        String type = getTypeDeclare(value.getClass());

        StringBuilder builder = new StringBuilder();

        builder.append(type)
                .append(" ")
                .append(var)
                .append("=")
                .append(node.compile(env))
                .append(";");

        return builder.toString();
    }

    public boolean isThisNodeSpited() {
        return isThisNodeSpited;
    }

    protected Object evalCached(JustContext env) {
        Object result = env.getCache(hashCode());

        if (result == null) {
            result = eval(env);

            env.putCache(hashCode(), result);
        }

        return result;
    }

    @Override
    public String compile(JustContext env) {
        if (isConstNode) {
            Object obj = eval(env);

            if (obj instanceof Float) return obj.toString() + "F";
            else if (obj instanceof Long) return obj.toString() + "L";

            return obj.toString();
        } else if (isShouldSplit()) {
            return splitSubAstEval(env);
        }

        return super.compile(env);
    }

    @Override
    public String toString() {

        return "(" +
                operator().toString() +
                " " +
                leftChild().toString() +
                " " +
                rightChild().toString() +
                ")";
    }
}
