package com.lfkdsk.justel.compile.memory;

import java.util.HashMap;
import java.util.Map;

/**
 * Memory Class Loader
 * Load Class From Memory
 * Created by liufengkai on 2017/7/20.
 */
public class JustMemClassLoader extends ClassLoader {

    /**
     * Compile Bytes Map.
     * Key => Class Qualified Name
     * Value => JVM Byte Codes
     */
    private final Map<String, byte[]> compileBytesMap = new HashMap<>();

    public JustMemClassLoader(ClassLoader parent) {
        super(parent);
    }

    public JustMemClassLoader(ClassLoader parent,
                              Map<String, byte[]> compileBytesMap) {
        super(parent);
        this.compileBytesMap.putAll(compileBytesMap);
    }

    public byte[] addBytesClass(String qualifiedClassName, byte[] bytes) {
        return compileBytesMap.put(qualifiedClassName, bytes);
    }

    @Override
    protected Class<?> findClass(String qualifiedClassName) throws ClassNotFoundException {
        byte[] bytes = compileBytesMap.remove(qualifiedClassName);
        if (bytes != null) {
            return defineClass(qualifiedClassName, bytes, 0, bytes.length);
        }
        return super.findClass(qualifiedClassName);
    }
}
