package com.lfkdsk.justel.ast.operators;

import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.ast.function.OperatorExpr;
import com.lfkdsk.justel.context.JustContext;

import java.util.List;

public class InOp extends OperatorExpr{
    public InOp(List<AstNode> children) {
        super(children);
    }

    @Override
    public Object eval(JustContext env) {
        return super.eval(env);
    }

    @Override
    public String compile(JustContext env) {
        return super.compile(env);
    }
}
