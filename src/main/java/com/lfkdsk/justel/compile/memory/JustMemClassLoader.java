package com.lfkdsk.justel.compile.memory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    /**
     * compiled name set
     */
    private final Set<String> compiledNameSet = new HashSet<>();

    public JustMemClassLoader(ClassLoader parent) {
        super(parent);
    }

    public JustMemClassLoader(ClassLoader parent,
                              Map<String, byte[]> compileBytesMap) {
        super(parent);
        this.compileBytesMap.putAll(compileBytesMap);
    }

    /**
     * add bytes class
     *
     * @param qualifiedClassName class-qualified-name
     * @param bytes              bytes
     * @return add to map
     */
    byte[] addBytesClass(String qualifiedClassName, byte[] bytes) {
        return compileBytesMap.put(qualifiedClassName, bytes);
    }

    @Override
    protected Class<?> findClass(String qualifiedClassName) throws ClassNotFoundException {
        if (!compiledNameSet.contains(qualifiedClassName)) {
            byte[] bytes = compileBytesMap.remove(qualifiedClassName);
            if (bytes != null) {
                compiledNameSet.add(qualifiedClassName);
                return defineClass(qualifiedClassName, bytes, 0, bytes.length);
            }
        }
        return super.findClass(qualifiedClassName);
    }
}
