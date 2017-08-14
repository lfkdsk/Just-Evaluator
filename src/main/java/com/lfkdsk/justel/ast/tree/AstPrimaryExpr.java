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
 * Primary Expr
 * primary expr = ( expr ) | number | id | string | boolean
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/26.
 * @see com.lfkdsk.justel.compile.compiler.JustCompilerImpl
 */
public class AstPrimaryExpr extends AstList {

    public AstPrimaryExpr(List<AstNode> children) {
        super(children, AstNode.PRIMARY_EXPR);
    }

    /**
     * Default Create Node
     * Check create Method in `BnfCom.Factory.get` Method
     *
     * @param c list of AstNode
     * @return single node or primary
     * @see com.lfkdsk.justel.parser.BnfCom.Factory
     */
    public static AstNode create(List<AstNode> c) {
        return c.size() == 1 ? c.get(0) : new AstPrimaryExpr(c);
    }

    /**
     * Operand Object
     *
     * @param expr Primary Expr
     * @return prefix Node
     */
    private Object operand(AstPrimaryExpr expr) {
        return expr.child(0);
    }

    /**
     * return last postfix
     *
     * @param expr expr
     * @param nest nest index
     * @return postfix expr
     */
    private AstPostfixExpr postfix(AstPrimaryExpr expr, int nest) {
        return (AstPostfixExpr) expr.child(expr.childCount() - nest - 1);
    }

    /**
     * check has postfix?
     *
     * @param expr expr
     * @param nest nest index
     * @return has postfix?
     */
    private boolean hasPostfix(AstPrimaryExpr expr, int nest) {
        return expr.childCount() - nest > 1;
    }

    /**
     * Recursion Method : Check Sub Expr
     *
     * @param env  context
     * @param expr this expr
     * @param nest nest index
     * @return return obj / sub expr
     */
    private Object evalSubExpr(JustContext env,
                               AstPrimaryExpr expr,
                               int nest) {
        if (hasPostfix(expr, nest)) {
            Object target = evalSubExpr(env, expr, nest + 1);
            return postfix(expr, nest).eval(env, target);
        } else {
            return ((AstNode) operand(expr)).eval(env);
        }
    }

    private Object compileSubExpr(JustContext env,
                                  AstPrimaryExpr expr,
                                  StringBuilder builder,
                                  int nest) {
        if (hasPostfix(expr, nest)) {
            Object target = compileSubExpr(env, expr, builder, nest + 1);

            return postfix(expr, nest).compile(env, target, builder);
        } else {
            String compileVar = ((AstNode) operand(expr)).compile(env);
            builder.append(compileVar);

            return env.get(compileVar);
        }
    }

    @Override
    public Object eval(JustContext env) {
        return evalSubExpr(env, this, 0);
    }

    @Override
    public String compile(JustContext env) {
        StringBuilder builder = new StringBuilder();
        compileSubExpr(env, this, builder, 0);

        return builder.toString();
    }
}
