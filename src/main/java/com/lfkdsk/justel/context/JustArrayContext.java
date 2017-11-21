package com.lfkdsk.justel.context;

import com.lfkdsk.justel.ast.function.ExtendFunctionExpr;
import com.lfkdsk.justel.compile.generate.Var;
import com.lfkdsk.justel.utils.collection.ArrayBinder;

import java.util.*;

public class JustArrayContext implements JustContext {

    private ArrayBinder<String, Object> indexBinder = new ArrayBinder<>();

    private List<String> commandList = new LinkedList<>();

    private Map<String, ExtendFunctionExpr> extFunc = new HashMap<>();

    private Map<Integer, Object> astCache = new HashMap<>();

    @Override
    public boolean contain(String name) {
        return this.indexBinder.containsKey(name);
    }

    @Override
    public Object get(String objName) {
        return indexBinder.get(objName);
    }

    @Override
    public Object put(String key, Object val) {
        return indexBinder.put(key, val);
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
        return commandList.add(command);
    }

    @Override
    public Collection<String> varsKeySet() {
        return indexBinder.keySet();
    }

    @Override
    public List<String> commandList() {
        return commandList;
    }

    @Override
    public boolean clearVars() {
        indexBinder.clear();
        commandList.clear();
        astCache.clear();

        return true;
    }

    @Override
    public int indexOf(String key) {
        return indexBinder.indexOf(key);
    }

    @Override
    public Object getWith(int index) {
        return indexBinder.getWith(index);
    }

    @Override
    public String generateVarAssignCode(Var var) {
        StringBuilder builder = new StringBuilder();

        String typeDeclare = Var.getTypeDeclare(var.getType());

        builder.append(typeDeclare).append(" ")
               .append(var.getName()).append("=")
               .append("((").append(var.getType().getCanonicalName()).append(")")
               .append("context.getWith(").append(indexOf(var.getName())).append(")").append(");");

        return builder.toString();
    }
}
