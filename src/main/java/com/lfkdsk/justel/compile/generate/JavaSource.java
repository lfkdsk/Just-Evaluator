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

    /**
     * Re-Format SourceCode -> Formatted Source Code
     * WARNING: JUST USED IN `toString` Method to Debug Source Code
     *
     * @param sourceCode no-format str
     * @return formatted source code
     */
    private static String reformatToPrint(String sourceCode) {
        StringBuilder builder = new StringBuilder(sourceCode);
        int start = 0, end = sourceCode.length();
        int index;
        while (start < end) {
            index = builder.indexOf(";", start);

            if (index == -1) break;

            builder.insert(index + 1, "\r\n");
            start = index + 1;
        }

        start = 0;

        while (start < end) {
            index = builder.indexOf("{", start);

            if (index == -1) break;

            builder.insert(index + 1, "\r\n");
            start = index + 1;
        }

        return builder.toString();
    }

    @Override
    public String toString() {
        return "<Java Source Code> : \n" +
                "PackageName : " + packageName + "\n" +
                "ClassName   : " + className + "\n" +
                "SourceCode  : \n" + reformatToPrint(sourceCode);
    }
}
