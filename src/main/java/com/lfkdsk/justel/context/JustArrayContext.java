package com.lfkdsk.justel.context;

import com.lfkdsk.justel.ast.function.ExtendFunctionExpr;
import com.lfkdsk.justel.compile.generate.Var;

import java.util.*;

public class JustArrayContext implements JustContext {

    private Map<String, Integer> indexMap = new HashMap<>();

    private ArrayList<Object> objectList = new ArrayList<>();

    private List<String> commandList = new LinkedList<>();

    private Map<String, ExtendFunctionExpr> extFunc = new HashMap<>();

    private Map<Integer, Object> astCache = new HashMap<>();

    @Override
    public boolean contain(String name) {
        return this.indexMap.containsKey(name);
    }

    @Override
    public Object get(String objName) {
        return objectList.get(indexMap.get(objName));
    }

    @Override
    public Object put(String key, Object val) {
        Integer index = indexMap.get(key);
        if (index != null) {
            objectList.set(index, val);
        } else {
            objectList.add(val);
            indexMap.put(key, objectList.size() - 1);
        }

        return val;
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
        return indexMap.keySet();
    }

    @Override
    public List<String> commandList() {
        return commandList;
    }

    @Override
    public boolean clearVars() {
        indexMap.clear();
        commandList.clear();
        astCache.clear();
        objectList.clear();

        return true;
    }

    @Override
    public int indexOf(String key) {
        return indexMap.get(key);
    }

    @Override
    public Object getWith(int index) {
        return objectList.get(index);
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
