package com.lfkdsk.justel.eval;

import com.lfkdsk.justel.context.JustContext;

public final class ExprBinder {
    private final JustContext contexts;

    private final String expr;

    private ExprBinder(JustContext contexts, String expr) {
        this.contexts = contexts;
        this.expr = expr;
    }

    public static ExprBinder of(JustContext contexts, String expr) {
        return new ExprBinder(contexts, expr);
    }

    public JustContext getContexts() {
        return contexts;
    }

    public String getExpr() {
        return expr;
    }
}
