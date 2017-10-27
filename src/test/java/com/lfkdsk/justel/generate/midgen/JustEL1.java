package com.lfkdsk.justel.generate.midgen;

import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.context.JustMapContext;
import com.lfkdsk.justel.eval.Expression;

public class JustEL1 implements Expression {
    @Override
    public Object eval(JustContext context) {
        String a = (String) context.get("a");
        String b = (String) context.get("b");
        Integer c = (java.lang.Integer) context.get("lfkdsk");
        Integer f = (java.lang.Integer) context.get("f");
        return c + f;
    }

    public static void main(String[] args) {
        new JustEL1().eval(new JustMapContext() {{
            put("lfkdsk", 10000);
            put("f", 10000);
            put("a", "llll");
            put("b", "llll");
        }});
    }
}
