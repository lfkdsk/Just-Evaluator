package com.lfkdsk.justel.context;

/**
 * Context => { key : value }
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/18.
 */
public interface JustContext {

    boolean contain(String name);

    Object get(String objName);

    Object put(String key, Object val);
}
