package com.lfkdsk.justel.compile.compiler;

import com.lfkdsk.justel.compile.generate.JavaSource;
import com.lfkdsk.justel.compile.memory.JustMemClassLoader;
import com.lfkdsk.justel.compile.memory.JustMemFileManager;
import com.lfkdsk.justel.exception.CompilerException;
import com.lfkdsk.justel.expr.Expression;

import javax.tools.*;
import java.io.IOException;
import java.util.Collections;

/**
 * Created by liufengkai on 2017/7/20.
 */
public class JustCompilerImpl implements JustCompiler {

    private final JavaCompiler compiler;

    private final StandardJavaFileManager stdFileManager;

    private final JustMemClassLoader memClassLoader;

    private DiagnosticCollector<JavaFileObject> diagnosticsReport;

    public JustCompilerImpl() {
        // initial compiler
        this.compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            throw new IllegalStateException("Can not bind to system java compiler");
        }
        this.diagnosticsReport = new DiagnosticCollector<>();
        this.stdFileManager = compiler.getStandardFileManager(diagnosticsReport, null, null);
        this.memClassLoader = new JustMemClassLoader(this.getClass().getClassLoader());
    }

    @Override
    public Expression compile(JavaSource code) {
        try (JustMemFileManager manager = new JustMemFileManager(stdFileManager, memClassLoader)) {
            JavaFileObject javaFileObject = manager.makeStringSource(code);

            JavaCompiler.CompilationTask compilationTask =
                    compiler.getTask(null, manager, diagnosticsReport, null, null, Collections.singletonList(javaFileObject));

            Boolean result = compilationTask.call();
            if (result == null || !result) {
                throw new CompilerException("Compilation Failed!");
            }

            return (Expression) loadClass(memClassLoader, code.className).newInstance();
        } catch (IOException e) {

            throw new CompilerException(
                    "Cannot com.lfkdsk.justel.compile Java Source Code With IO Exception \n"
                            + e.getMessage() + " \n : " + code.toString());
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {

            throw new CompilerException(e.getMessage());
        }
    }
}
