package com.lfkdsk.justel.compile.generate;

import java.nio.CharBuffer;

/**
 * Created by liufengkai on 2017/7/20.
 */
public final class JavaSource {
    public static final String GENERATE_DEFALUT_PACKAGE = "com.lfkdsk.justel.generatecode";

    public final String packageName;

    public final String className;

    public final String sourceCode;

    private CharSequence sourceCodeChar;

    public JavaSource(String packageName,
                      String className,
                      String sourceCode) {
        this.className = className;
        this.sourceCode = sourceCode;
        this.packageName = packageName;
    }

    public String getClassQualifiedName() {
        return packageName + "." + className;
    }

    public String getFileName() {
        return className + ".java";
    }

    public CharSequence getSourceCodeCharSequence() {
        return sourceCodeChar == null ? sourceCodeChar = CharBuffer.wrap(sourceCode) : sourceCodeChar;
    }

    @Override
    public String toString() {
        return "<Java Source Code> : \n" +
                "PackageName : " + packageName + "\n" +
                "ClassName   : " + className + "\n" +
                "SourceCode  : " + sourceCode + "\n";
    }
}
