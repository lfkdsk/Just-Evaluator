package com.lfkdsk.justel.eval;

import com.lfkdsk.justel.context.JustContext;

/**
 * Evaluable => To mark this node could be evaluated.
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/18.
 */
public interface Evaluable {

    /**
     * eval this node
     *
     * @param context context =>
     * @return the result name
     */
    Object eval(JustContext context);
}
