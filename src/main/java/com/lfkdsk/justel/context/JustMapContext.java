package com.lfkdsk.justel.context;

import com.lfkdsk.justel.ast.function.ExtendFunctionExpr;
import com.lfkdsk.justel.utils.ObjectHelper;

import java.util.*;

/**
 * Just Map Context
 *
 * @author liufengkai
 * Created by liufengkai on 2017/7/18.
 */
public class JustMapContext implements JustContext {

    private Map<String, Object> map = new HashMap<>();

    private List<String> commandList = new LinkedList<>();

    private Map<String, ExtendFunctionExpr> extFunc = new HashMap<>();

    private Map<Integer, Object> astCache = new HashMap<>();

    @Override
    public boolean contain(String name) {
        return map.containsKey(name);
    }

    @Override
    public Object remove(String key) {
        return map.remove(key);
    }

    @Override
    public Object get(String objName) {
        return map.get(objName);
    }

    @Override
    public Object put(String key, Object val) {
        return map.put(key, val);
    }

    @Override
    public Object getCache(Integer astHash) {
        return astCache.get(astHash);
    }

    @Override
    public Object putCache(Integer key, Object val) {
        return astCache.put(key, val);
    }

    @Override
    public ExtendFunctionExpr putExtendFunc(ExtendFunctionExpr expr) {
        return extFunc.put(expr.funcName(), expr);
    }

    @Override
    public ExtendFunctionExpr getExtendFunc(String name) {
        return extFunc.get(name);
    }

    @Override
    public Object command(String command) {
        ObjectHelper.requireNonNull(command, "command could not null");

        return commandList.add(command);
    }

    @Override
    public Collection<String> varsKeySet() {
        return map.keySet();
    }

    @Override
    public List<String> commandList() {
        return commandList;
    }

    @Override
    public List<String> functions() {
        return new ArrayList<>(extFunc.keySet());
    }

    @Override
    public boolean clearVars() {
        map.clear();
        return true;
    }
}
