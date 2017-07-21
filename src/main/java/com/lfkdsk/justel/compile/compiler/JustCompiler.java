package com.lfkdsk.justel.compile.compiler;

import com.lfkdsk.justel.compile.generate.JavaSource;
import com.lfkdsk.justel.expr.Expression;

/**
 * Created by liufengkai on 2017/7/20.
 */
public interface JustCompiler<T> {

  Expression compile(JavaSource code);

  @SuppressWarnings("unchecked")
  default Class<T> loadClass(ClassLoader loader, String classQualifiedName)
      throws ClassNotFoundException {
    return (Class<T>) loader.loadClass(classQualifiedName);
  }
}
