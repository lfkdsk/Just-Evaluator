package com.lfkdsk.justel.context;

import java.util.*;

/**
 * Just Map Context
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/18.
 */
public class JustMapContext implements JustContext {

    private Map<String, Object> map = new HashMap<>();

    private List<String> commandList = new LinkedList<>();

    private List<String> globalList = new LinkedList<>();

    @Override
    public boolean contain(String name) {
        return map.containsKey(name);
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
    public Object command(String command) {
        return commandList.add(command);
    }

    @Override
    public Object global(String command) {
        return globalList.add(command);
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
    public List<String> globalList() {
        return globalList;
    }
}
