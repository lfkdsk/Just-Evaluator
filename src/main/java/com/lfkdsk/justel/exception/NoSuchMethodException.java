package com.lfkdsk.justel.exception;

import com.lfkdsk.justel.ast.base.AstNode;

public class NoSuchMethodException extends EvalException {
    public NoSuchMethodException(String msg) {
        super(msg);
    }

    public NoSuchMethodException(String msg, AstNode tree) {
        super(msg, tree);
    }
}
