package com.lfkdsk.justel.eval;

import com.lfkdsk.justel.context.JustContext;

/**
 * Evaluable => To mark this node could be evaluated.
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/18.
 * @see Expression
 * @see ConstExpression
 * @see com.lfkdsk.justel.ast.base.AstNode
 */
@FunctionalInterface
public interface Evaluable {

    /**
     * call this node
     *
     * @param context context =>
     * @return the result name
     */
    Object eval(JustContext context);
}
