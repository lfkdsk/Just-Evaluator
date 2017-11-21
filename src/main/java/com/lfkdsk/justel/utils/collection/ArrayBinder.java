package com.lfkdsk.justel.utils.collection;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

import static com.lfkdsk.justel.utils.collection.ArrayBinder.IndexEntry.of;

public class ArrayBinder<Key, Value> implements Map<Key, Value> {

    private Map<Key, Integer> indexMap = new HashMap<>();

    private ArrayList<Value> objectList = new ArrayList<>();

    public int indexOf(Key key) {
        return indexMap.get(key);
    }

    public Value getWith(int index) {
        return objectList.get(index);
    }

    @Override
    public int size() {
        if (indexMap.size() == objectList.size()) {
            return objectList.size();
        }

        throw new RuntimeException("indexMap Size && objList Size aren't equal ");
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return indexMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return indexMap.containsKey(value);
    }

    @Override
    public Value get(Object key) {
        Integer index = indexMap.get(key);
        if (index == null || index > size()) {
            return null;
        }

        return objectList.get(index.intValue());
    }

    @Override
    public Value put(Key key, Value value) {
        Integer index = indexMap.get(key);
        if (index != null) {
            objectList.set(index, value);
        } else {
            objectList.add(value);
            // set index
            indexMap.put(key, objectList.size() - 1);
        }

        return value;
    }

    @Override
    public Value remove(Object key) {
        Integer index = indexMap.remove(key);
        if (index == null) {
            return null;
        }

        Value value = objectList.get(index);
        objectList.remove(index);
        return value;
    }

    @Override
    public void putAll(@NotNull Map<? extends Key, ? extends Value> m) {
        Set<? extends Entry<? extends Key, ? extends Value>> entrySet = m.entrySet();
        for (Entry<? extends Key, ? extends Value> entry : entrySet) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        this.indexMap.clear();
        this.objectList.clear();
    }

    @NotNull
    @Override
    public Set<Key> keySet() {
        return indexMap.keySet();
    }

    @NotNull
    @Override
    public Collection<Value> values() {
        return objectList;
    }

    @NotNull
    @Override
    public Set<Map.Entry<Key, Value>> entrySet() {
        return keySet()
                .stream()
                .map(key -> of(key, get(key), indexOf(key)))
                .collect(Collectors.toSet());
    }

    static final class IndexEntry<Key, Value> implements Map.Entry<Key, Value> {
        final Key key;
        final Value value;
        final int index;

        IndexEntry(Key key, Value value, int index) {
            this.key = key;
            this.value = value;
            this.index = index;
        }

        @Override
        public Key getKey() {
            return key;
        }

        @Override
        public Value getValue() {
            return value;
        }

        @Override
        public Value setValue(Value value) {
            return value;
        }

        static <Key, Value> IndexEntry<Key, Value> of(Key key, Value value, int index) {
            return new IndexEntry<>(key, value, index);
        }
    }
}
