/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.ast.operators;

import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.ast.function.OperatorExpr;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.token.SepToken;

import java.util.List;

import static com.lfkdsk.justel.utils.NumberUtils.computeAmpersandValue;
import static com.lfkdsk.justel.utils.TypeUtils.isNumber;

/**
 * & Operator
 * eq: left & right
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/26.
 * @see OperatorExpr
 */
public class AmpersandOp extends OperatorExpr {

    public AmpersandOp(List<AstNode> children) {
        super(children, AstNode.AMPERSAND_OP);
    }

    @Override
    public String functionName() {
        return SepToken.AMPERSAND_TOKEN.getText();
    }

    @Override
    public Object eval(JustContext env) {
        Object leftValue = leftChild().eval(env);
        Object rightValue = rightChild().eval(env);

        if (isNumber(leftValue) && isNumber(rightValue)) {
            return computeAmpersandValue((Number) leftValue, (Number) rightValue);
        }

        return super.eval(env);
    }
}
