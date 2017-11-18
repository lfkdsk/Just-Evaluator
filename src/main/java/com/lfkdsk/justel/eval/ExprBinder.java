package com.lfkdsk.justel.eval;

import com.lfkdsk.justel.context.JustContext;
import lombok.Getter;

public final class ExprBinder {
    @Getter
    private final JustContext contexts;

    @Getter
    private final String expr;

    private ExprBinder(JustContext contexts, String expr) {
        this.contexts = contexts;
        this.expr = expr;
    }

    public static ExprBinder of(JustContext contexts, String expr) {
        return new ExprBinder(contexts, expr);
    }
}
