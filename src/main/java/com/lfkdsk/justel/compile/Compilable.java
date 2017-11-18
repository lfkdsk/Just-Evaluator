package com.lfkdsk.justel.compile;

import com.lfkdsk.justel.context.JustContext;

/**
 * Created by liufengkai on 2017/7/18.
 */
@FunctionalInterface
public interface Compilable {
    String compile(JustContext context);
}
