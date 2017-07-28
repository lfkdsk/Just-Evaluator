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
import com.lfkdsk.justel.utils.NumberUtils;

import java.util.List;

import static com.lfkdsk.justel.utils.TypeUtils.*;

/**
 * Created by liufengkai on 2017/7/26.
 */
public class EqualOp extends OperatorExpr {
    public EqualOp(List<AstNode> children) {
        super(children, AstNode.EQUAL_OP);
    }

    @Override
    public Object eval(JustContext env) {
        Object left = leftChild().eval(env);
        Object right = rightChild().eval(env);

        // boolean == boolean
        if (isBoolean(left) && isBoolean(right)) {

            return left.equals(right);
        } else if (isNumber(left) && isNumber(right)) {

            // num == num
            return left.equals(right);
        } else if (isString(left) && isString(right)) {

            return left.equals(right);
        } else if (isNumberLiteral(left) && isNumberLiteral(right)) {
            NumberToken leftToken = ((NumberLiteral) left).numberToken();
            NumberToken rightToken = ((NumberLiteral) right).numberToken();
            Object leftValue = NumberUtils.computeValueToken(leftToken);
            Object rightValue = NumberUtils.computeValueToken(rightToken);

            // id(num) == id(num)
            return leftValue.equals(rightValue);
        }


        return super.eval(env);
    }
}
