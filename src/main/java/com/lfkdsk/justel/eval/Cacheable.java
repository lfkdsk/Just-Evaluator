package com.lfkdsk.justel.eval;

import com.lfkdsk.justel.context.JustContext;

/**
 * Created by liufengkai on 2017/9/28.
 */
public interface Cacheable {
    /**
     * call this node
     *
     * @param context context =>
     * @return the result name
     */
    Object evalCache(JustContext context);
}
