package com.lfkdsk.justel.generate.javagen;

import java.nio.CharBuffer;

import static com.lfkdsk.justel.utils.FormatUtils.beautifulPrint;
import static com.lfkdsk.justel.utils.FormatUtils.insertNewLine;

/**
 * Java Source :
 * - package name
 * - class name
 * - source code
 * - code => char (cache)
 *
 * @author liufengkai
 * Created by liufengkai on 2017/7/20.
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

    /**
     * Re-Format SourceCode -> Formatted Source Code
     * WARNING: JUST USED IN `toString` Method to Debug Source Code
     *
     * @return formatted source code
     */
    private String reformatToPrint() {
        StringBuilder builder = new StringBuilder(sourceCode);

        builder = insertNewLine(builder, "{", "\r\n║");
        builder = insertNewLine(builder, ";", "\r\n║");

        String[] args = {
                "<Java Source Code> : ",
                "PackageName : " + packageName,
                "ClassName   : " + className,
                "SourceCode  : ",
                builder.toString()
        };

        return beautifulPrint(args);
    }

    @Override
    public String toString() {

        return reformatToPrint();
    }
}
