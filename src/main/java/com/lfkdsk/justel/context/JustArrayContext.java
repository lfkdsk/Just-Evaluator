package com.lfkdsk.justel.context;

import com.lfkdsk.justel.ast.function.ExtendFunctionExpr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JustArrayContext implements JustContext {

    private JustMapContext innerContext = new JustMapContext();

    private List<String> names = new ArrayList<>(10);

    private List<Object> vars = new ArrayList<>(10);

    @Override
    public boolean contain(String name) {
        return innerContext.contain(name);
    }

    @Override
    public Object get(String objName) {
        int index = names.indexOf(objName);
        return vars.get(index);
    }

    @Override
    public Object put(String key, Object val) {
        innerContext.put(key, val);
        names.add(key);
        vars.add(val);

        return val;
    }

    @Override
    public Object getCache(Integer astHash) {
        return innerContext.getCache(astHash);
    }

    @Override
    public Object putCache(Integer key, Object val) {
        return innerContext.putCache(key, val);
    }

    @Override
    public ExtendFunctionExpr putExtendFunc(String name, ExtendFunctionExpr expr) {
        return innerContext.putExtendFunc(name, expr);
    }

    @Override
    public ExtendFunctionExpr getExtendFunc(String name) {
        return innerContext.getExtendFunc(name);
    }

    @Override
    public Object command(String command) {
        return innerContext.command(command);
    }

    @Override
    public Collection<String> varsKeySet() {
        return innerContext.varsKeySet();
    }

    @Override
    public List<String> commandList() {
        return innerContext.commandList();
    }

    @Override
    public boolean clearVars() {
        innerContext.clearVars();
        names.clear();
        vars.clear();
        
        return false;
    }
}
