package com.lfkdsk.justel.exception;

import com.lfkdsk.justel.ast.base.AstNode;

/**
 * Compiler Exception
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/20.
 */
public class CompilerException extends RuntimeException {

    public CompilerException(String message) {
        super(message);
    }

    public CompilerException(String msg, AstNode tree) {
        super(msg + " " + tree.location());
    }
}
