package com.lfkdsk.justel.compile.compiler;

import com.lfkdsk.justel.generate.javagen.JavaSource;
import com.lfkdsk.justel.eval.Expression;

/**
 * Just Compiler Class
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/20.
 */
public interface JustCompiler {

    /**
     * Compile JavaSource Code
     *
     * @param code source code
     * @return Expression
     * @see Expression
     * @see JavaSource
     */
    Expression compile(JavaSource code);

    /**
     * Load Class => ClassLoader
     *
     * @param loader             loader
     * @param classQualifiedName class's qualified name
     * @param <T>                spec clazz
     * @return object
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    default <T> Class<T> loadClass(ClassLoader loader, String classQualifiedName)
            throws ClassNotFoundException {
        return (Class<T>) loader.loadClass(classQualifiedName);
    }
}
