package com.lfkdsk.justel.compile.memory;

import com.lfkdsk.justel.compile.generate.JavaSource;

import javax.tools.*;
import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

/**
 * Created by liufengkai on 2017/7/20.
 */
public class JustMemFileManager extends ForwardingJavaFileManager<JavaFileManager> {

    private final JustMemClassLoader classLoader;

    public JustMemFileManager(JavaFileManager fileManager, JustMemClassLoader memClassLoader) {
        super(fileManager);
        this.classLoader = memClassLoader;
    }

    @Override
    public void close() throws IOException {
        super.close();
    }

    public JavaFileObject makeStringSource(JavaSource source) {
        return new MemInputJavaFileObject(source);
    }

    @Override
    public JavaFileObject getJavaFileForOutput(Location location, String className,
                                               JavaFileObject.Kind kind, FileObject sibling) throws IOException {
        if (kind == JavaFileObject.Kind.CLASS) {
            return new MemOutputJavaFileObject(className);
        } else {
            return super.getJavaFileForOutput(location, className, kind, sibling);
        }
    }

    static class MemInputJavaFileObject extends SimpleJavaFileObject {
        final JavaSource source;

        protected MemInputJavaFileObject(JavaSource source) {
            super(URI.create("string:///" + source.getFileName()), Kind.SOURCE);
            this.source = source;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
            return source.getSourceCodeCharSequence();
        }
    }

    class MemOutputJavaFileObject extends SimpleJavaFileObject {

        final String className;

        MemOutputJavaFileObject(String className) {
            super(URI.create("string:///" + className), Kind.CLASS);
            this.className = className;
        }

        @Override
        public OutputStream openOutputStream() throws IOException {
            return new FilterOutputStream(new ByteArrayOutputStream() {
                @Override
                public void close() throws IOException {
                    super.close();
                    classLoader.addBytesClass(className, this.toByteArray());
                }
            });
        }
    }
}
