package com.lfkdsk.justel.context;

import java.util.Collection;

/**
 * Context => { key : name }
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/18.
 */
public interface JustContext {

    boolean contain(String name);

    Object get(String objName);

    Object put(String key, Object val);

    Collection<String> keySet();
}
