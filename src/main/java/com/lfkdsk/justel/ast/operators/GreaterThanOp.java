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
import com.lfkdsk.justel.token.Token;

import java.util.List;

import static com.lfkdsk.justel.utils.NumberUtils.computeValue;
import static com.lfkdsk.justel.utils.TypeUtils.isComparable;
import static com.lfkdsk.justel.utils.TypeUtils.isNumber;

/**
 * > Operator
 * eq: expr > expr
 *
 * @author liufengkai
 * Created by liufengkai on 2017/7/31.
 * @see OperatorExpr
 */
public class GreaterThanOp extends OperatorExpr {

    public GreaterThanOp(List<AstNode> children) {
        super(children, Token.GREAT_THAN);
    }

    @Override
    public String funcName() {
        return SepToken.GREAT_TOKEN.getText();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object eval(JustContext env) {
        Object left = leftChild().eval(env);
        Object right = rightChild().eval(env);

        if (isNumber(left) && isNumber(right)) {
            return Double.compare(computeValue(left), computeValue(right)) > 0;
        } else if (isComparable(left) && isComparable(right)) {
            return ((Comparable) left).compareTo(right) > 0;
        }

        return super.eval(env);
    }
}
