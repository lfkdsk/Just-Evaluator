package com.lfkdsk.justel.context;

import com.lfkdsk.justel.ast.function.ExtendFunctionExpr;
import com.lfkdsk.justel.compile.generate.Var;

import java.util.*;

public class JustArrayContext implements JustContext {


    private JustMapContext inner = new JustMapContext();

    private Map<String, Integer> indexMap = new HashMap<>();

    private ArrayList<Object> objectList = new ArrayList<>();

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
        return inner.getCache(astHash);
    }

    @Override
    public Object putCache(Integer key, Object val) {
        return inner.putCache(key, val);
    }

    @Override
    public ExtendFunctionExpr putExtendFunc(String name, ExtendFunctionExpr expr) {
        return inner.putExtendFunc(name, expr);
    }

    @Override
    public ExtendFunctionExpr getExtendFunc(String name) {
        return inner.getExtendFunc(name);
    }

    @Override
    public Object command(String command) {
        return inner.command(command);
    }

    @Override
    public Collection<String> varsKeySet() {
        return indexMap.keySet();
    }

    @Override
    public List<String> commandList() {
        return inner.commandList();
    }

    @Override
    public boolean clearVars() {
        inner.clearVars();
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
               .append(var.name).append("=")
               .append("((").append(var.getType().getCanonicalName()).append(")")
               .append("context.getWith(").append(indexOf(var.name)).append(")").append(");");

        return builder.toString();
    }
}
