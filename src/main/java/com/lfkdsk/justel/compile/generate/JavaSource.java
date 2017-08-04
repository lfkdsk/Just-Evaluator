package com.lfkdsk.justel.compile.generate;

import java.nio.CharBuffer;

/**
 * Java Source :
 * - package name
 * - class name
 * - source code
 * - code => char (cache)
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/20.
 */
public final class JavaSource {

    /**
     * default generate package
     */
    public static final String GENERATE_DEFAULT_PACKAGE = "com.lfkdsk.justel.generatecode";

    public final String packageName;

    public final String className;

    public final String sourceCode;

    /**
     * source code => code char
     */
    private CharSequence sourceCodeChar;

    public JavaSource(String packageName,
                      String className,
                      String sourceCode) {
        this.className = className;
        this.sourceCode = sourceCode;
        this.packageName = packageName;
    }

    /**
     * class qualified name
     *
     * @return package name + class name.
     */
    public String getClassQualifiedName() {
        return packageName + "." + className;
    }

    /**
     * file name
     *
     * @return xxx.java
     */
    public String getFileName() {
        return className + ".java";
    }

    /**
     * return char sequence
     *
     * @return char sequence.
     */
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
