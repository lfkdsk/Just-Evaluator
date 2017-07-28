/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.literal;

import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.exception.EvalException;
import com.lfkdsk.justel.token.Token;

/**
 * ID Literal.
 * => The Language support's ID Literal
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/26.
 */
public class IDLiteral extends Literal {

    public IDLiteral(Token token) {
        super(token);
    }

    @Override
    public Object eval(JustContext env) {
        Object value = env.get(name());

        if (value == null) {
            throw new EvalException("undefined name: " + name(), this);
        } else {
            return value;
        }
    }
}
