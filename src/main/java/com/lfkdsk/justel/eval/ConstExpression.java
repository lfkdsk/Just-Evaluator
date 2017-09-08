package com.lfkdsk.justel.eval;

import com.lfkdsk.justel.context.JustContext;

/**
 * Constant Expression
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/8/11.
 * @see com.lfkdsk.justel.ast.tree.AstProgram
 * @see com.lfkdsk.justel.JustEL
 */
public final class ConstExpression implements Expression {

    private final Object constVal;

    public ConstExpression(Object constVal) {
        this.constVal = constVal;
    }

    @Override
    public Object eval(JustContext context) {
        return eval();
    }

    @Override
    public Object eval() {
        return constVal;
    }
}
