package com.lfkdsk.justel.context;

import com.lfkdsk.justel.ast.function.ExtendFunctionExpr;

import java.util.Collection;
import java.util.List;

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

    Object getCache(Integer astHash);

    Object putCache(Integer key, Object val);

    ExtendFunctionExpr putExtendFunc(String name, ExtendFunctionExpr expr);

    ExtendFunctionExpr getExtendFunc(String name);

    /**
     * Warning:!!!
     * Add Code to Java Source Code Straightly
     *
     * @param command command line
     * @return value
     */
    Object command(String command);

    Collection<String> varsKeySet();

    List<String> commandList();

    boolean clearVars();
}
