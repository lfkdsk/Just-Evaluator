/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.eval;

import com.lfkdsk.justel.context.JustContext;

/**
 * Expression : Evaluable Expression.
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/20.
 * @see Evaluable
 * @see com.lfkdsk.justel.JustEL
 */
@FunctionalInterface
public interface Expression extends Evaluable {

    /**
     * Eval value with context
     *
     * @param context context =>
     * @return value
     */
    @Override
    Object eval(JustContext context);

    /**
     * Eval Const Value
     *
     * @return value
     */
    default Object eval() {
        return eval(null);
    }
}
