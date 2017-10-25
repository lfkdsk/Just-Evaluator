package com.lfkdsk.justel.generate.midgen;

import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.context.JustMapContext;
import com.lfkdsk.justel.eval.Expression;

public class JustEL1 implements Expression {
    @Override
    public Object eval(JustContext context) {
        boolean a = true;
        return a;
    }

    public static void main(String[] args) {
        new JustEL1().eval(new JustMapContext() {{
            put("lfkdsk", 10000);
        }});
    }
}
