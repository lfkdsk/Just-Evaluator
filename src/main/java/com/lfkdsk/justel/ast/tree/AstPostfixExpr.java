/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.ast.tree;

import com.lfkdsk.justel.context.JustContext;

/**
 * Abstract Postfix Expr =>
 * - [ expr ]
 * - .id
 * - ( args | null )
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/27.
 * @see com.lfkdsk.justel.ast.operators.ArrayIndexExpr
 * @see com.lfkdsk.justel.ast.operators.DotExpr
 * @see AstFuncArguments
 */
public interface AstPostfixExpr {

    /**
     * Evaluate Postfix Expr
     *
     * @param env   env
     * @param value name
     * @return eval result
     */
    Object eval(JustContext env, Object value);

    Object compile(JustContext env, Object value, StringBuilder builder);

    String postfix();
}

