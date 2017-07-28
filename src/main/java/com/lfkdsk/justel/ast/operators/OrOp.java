/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.ast.operators;

import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.token.SepToken;
import com.lfkdsk.justel.token.Token;

import java.util.List;

import static com.lfkdsk.justel.utils.TypeUtils.isBoolean;

/**
 * ||
 * Created by liufengkai on 2017/7/28.
 */
public class OrOp extends OperatorExpr {
    public OrOp(List<AstNode> children) {
        super(children, Token.OR);
    }

    @Override
    public String functionName() {
        return SepToken.OR_TOKEN.getText();
    }

    @Override
    public Object eval(JustContext env) {
        Object leftValue = leftChild().eval(env);
        Object rightValue = rightChild().eval(env);

        if (isBoolean(leftValue) && isBoolean(rightValue)) {
            return leftValue == Boolean.TRUE
                    || rightValue == Boolean.TRUE;
        }

        return super.eval(env);
    }
}
