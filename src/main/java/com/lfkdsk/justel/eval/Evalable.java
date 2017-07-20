package com.lfkdsk.justel.eval;

import com.lfkdsk.justel.context.JustContext;

/**
 * Created by liufengkai on 2017/7/18.
 */
public interface Evalable {
    Object eval(JustContext context);
}
