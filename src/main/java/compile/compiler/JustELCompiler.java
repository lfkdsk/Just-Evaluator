package compile.compiler;

import compile.generate.JavaSourceCode;
import expr.Expression;

/**
 * Created by liufengkai on 2017/7/20.
 */
public interface JustELCompiler<T> {

    Expression compile(JavaSourceCode code);

    @SuppressWarnings("unchecked")
    default Class<T> loadClass(ClassLoader loader, String classQualifiedName)
            throws ClassNotFoundException {
        return (Class<T>) loader.loadClass(classQualifiedName);
    }


}
