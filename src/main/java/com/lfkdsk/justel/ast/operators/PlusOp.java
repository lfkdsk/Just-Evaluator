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
import com.lfkdsk.justel.literal.NumberLiteral;
import com.lfkdsk.justel.token.NumberToken;
import com.lfkdsk.justel.token.SepToken;
import com.lfkdsk.justel.token.Token;

import java.util.List;

import static com.lfkdsk.justel.utils.NumberUtils.computeValue;
import static com.lfkdsk.justel.utils.TypeUtils.isNumber;
import static com.lfkdsk.justel.utils.TypeUtils.isNumberLiteral;
import static com.lfkdsk.justel.utils.TypeUtils.isString;

/**
 * + Operator:
 * left + operator
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/26.
 */
public class PlusOp extends OperatorExpr {
    public PlusOp(List<AstNode> children) {
        super(children, Token.PLUS_OP);
    }

    @Override
    public String functionName() {
        return SepToken.PLUS_TOKEN.getText();
    }

    private Object computePlus(NumberToken leftToken, NumberToken rightToken) {
        return (leftToken.integerValue() + leftToken.longValue()
                + leftToken.floatValue() + leftToken.doubleValue())
                + (rightToken.integerValue() + leftToken.longValue()
                + rightToken.floatValue() + rightToken.doubleValue());
    }

    @Override
    public Object eval(JustContext env) {
        Object left = leftChild().eval(env);
        Object right = rightChild().eval(env);

        if (isString(left) && isString(right)) {
            return String.valueOf(left) + String.valueOf(right);
        } else if (isNumberLiteral(left) && isNumberLiteral(right)) {
            NumberToken leftToken = ((NumberLiteral) left).numberToken();
            NumberToken rightToken = ((NumberLiteral) right).numberToken();

            return computePlus(leftToken, rightToken);
        } else if (isNumber(left) && isNumber(right)) {

            return computeValue(left, right);
        }

        return super.eval(env);
    }
}
