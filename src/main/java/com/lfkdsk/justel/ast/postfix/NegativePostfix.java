/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.ast.postfix;

import com.lfkdsk.justel.ast.base.AstList;
import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.context.JustContext;

import java.util.List;

import static com.lfkdsk.justel.utils.NumberUtils.computeNegative;
import static com.lfkdsk.justel.utils.TypeUtils.isNumber;

/**
 * -
 * Created by liufengkai on 2017/7/26.
 */
public class NegativePostfix extends AstList {
    public NegativePostfix(List<AstNode> children) {
        super(children, AstNode.NEGATIVE_OP);
    }

    public AstNode operand() {
        return child(0);
    }

    @Override
    public String toString() {
        return "-" + operand();
    }

    @Override
    public Object eval(JustContext env) {
        Object value = this.operand().eval(env);

        if (isNumber(value)) {

            return computeNegative((Number) value);
        }

        return super.eval(env);
    }

}
