package com.lfkdsk.justel.generate.midgen;

import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.eval.Expression;

public class JustEL1 implements Expression {
    @Override
    public Object eval(JustContext context) {
        int lfkdsk = (java.lang.Integer) context.get("lfkdsk");
        return "Hello World!";
    }
}
