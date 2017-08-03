package com.lfkdsk.justel.compile.compiler;

import com.lfkdsk.justel.compile.generate.JavaSource;
import com.lfkdsk.justel.compile.memory.JustMemClassLoader;
import com.lfkdsk.justel.compile.memory.JustMemFileManager;
import com.lfkdsk.justel.exception.CompilerException;
import com.lfkdsk.justel.eval.Expression;

import javax.tools.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Just Compiler Implementation
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/20.
 */
public class JustCompilerImpl implements JustCompiler {

    /**
     * Java compiler
     */
    private final JavaCompiler compiler;

    /**
     * memory class loader
     */
    private final JustMemClassLoader memClassLoader;

    /**
     * diagnostic report
     */
    private final DiagnosticCollector<JavaFileObject> diagnosticsReport;

    /**
     * memory manager
     */
    private final JustMemFileManager manager;

    /**
     * class-qualified-name => Expression
     */
    private final Map<String, Expression> memCompilerCache;

    public JustCompilerImpl() {
        // initial compiler
        this.compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            throw new IllegalStateException("Can not bind to system java compiler");
        }
        this.diagnosticsReport = new DiagnosticCollector<>();
        this.memClassLoader = new JustMemClassLoader(this.getClass().getClassLoader());
        this.manager = new JustMemFileManager(compiler.getStandardFileManager(diagnosticsReport, null, null)
                , memClassLoader);
        this.memCompilerCache = new HashMap<>();
    }

    @Override
    public Expression compile(JavaSource code) {

        try {
            // found expression in cache.
            if (memCompilerCache.containsKey(code.getClassQualifiedName())) {
                return memCompilerCache.get(code.getClassQualifiedName());
            }

            JavaFileObject javaFileObject = manager.makeStringSource(code);

            JavaCompiler.CompilationTask compilationTask =
                    compiler.getTask(null, manager, diagnosticsReport, null, null,
                            Collections.singletonList(javaFileObject));

            Boolean result = compilationTask.call();
            if (result == null || !result) {
                throw new CompilerException(
                        "Compilation Failed! " + diagnosticsReport.getDiagnostics().toString());
            }

            Expression expr =
                    (Expression) loadClass(memClassLoader, code.getClassQualifiedName()).newInstance();
            // add release expr to cache.
            memCompilerCache.put(code.getClassQualifiedName(), expr);

            return expr;
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {

            e.printStackTrace();
            throw new CompilerException(e.getMessage());
        }
    }
}
