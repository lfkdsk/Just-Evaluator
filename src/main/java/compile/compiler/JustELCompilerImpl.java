package compile.compiler;

import compile.generate.JavaSourceCode;
import expr.Expression;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * Created by liufengkai on 2017/7/20.
 */
public class JustELCompilerImpl implements JustELCompiler {

    private final JavaCompiler compiler;

    public JustELCompilerImpl() {
        // initial compiler
        compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            throw new IllegalStateException("Can not bind to system java compiler");
        }
    }

    @Override
    public Expression compile(JavaSourceCode code) {
        return null;
    }
}
