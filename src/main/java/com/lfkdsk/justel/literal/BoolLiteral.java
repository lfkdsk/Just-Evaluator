/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.literal;

import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.token.Token;

import static com.lfkdsk.justel.token.BoolToken.BooleanEnum.TRUE;
import static com.lfkdsk.justel.token.BoolToken.booleanValue;

/**
 * Boolean Literal => Support two Boolean Value.
 * - true
 * - false
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/26.
 */
public class BoolLiteral extends Literal {

    public BoolLiteral(Token token) {
        super(token);
    }

    public boolean value() {
        return booleanValue(token.getText()) == TRUE;
    }

    @Override
    public Object eval(JustContext env) {
        return value() ? Boolean.TRUE : Boolean.FALSE;
    }
}
