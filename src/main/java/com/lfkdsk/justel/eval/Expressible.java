package com.lfkdsk.justel.eval;

/**
 * Could convert to expr
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/9/8.
 * @see com.lfkdsk.justel.ast.base.AstNode
 * @see com.lfkdsk.justel.JustEL
 */
public interface Expressible {

    /**
     * convert ast node to convert
     *
     * @return Expression
     */
    Expression expr();
}
