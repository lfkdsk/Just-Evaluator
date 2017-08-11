package com.lfkdsk.justel.eval;

import com.lfkdsk.justel.context.JustContext;

/**
 * Created by liufengkai on 2017/8/11.
 */
public final class ConstExpression implements Expression {

    private final Object constVal;

    public ConstExpression(Object constVal) {
        this.constVal = constVal;
    }

    @Override
    public Object eval(JustContext context) {
        return constVal;
    }
}
