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

        final char TOP_LEFT_CORNER = '╔';
        final char BOTTOM_LEFT_CORNER = '╚';
        final char MIDDLE_CORNER = '╟';
        final char HORIZONTAL_DOUBLE_LINE = '║';
        final String DOUBLE_DIVIDER = "════════════════════════════════════════════";
        final String SINGLE_DIVIDER = "────────────────────────────────────────────";
        final String TOP_BORDER = TOP_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
        final String BOTTOM_BORDER = BOTTOM_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
        final String MIDDLE_BORDER = MIDDLE_CORNER + SINGLE_DIVIDER + SINGLE_DIVIDER;

        StringBuilder parent = new StringBuilder();
        parent.append(TOP_BORDER);

        String[] args = {
                "<Java Source Code> : ",
                "PackageName : " + packageName,
                "ClassName   : " + className,
                "SourceCode  : "
        };

        for (String arg : args) {
            parent.append("\t\n")
                  .append(HORIZONTAL_DOUBLE_LINE)
                  .append(arg)
                  .append("\t\n")
                  .append(MIDDLE_BORDER);
        }

        StringBuilder builder = new StringBuilder(sourceCode);
        builder = insertNewLine(builder, ";");
        builder = insertNewLine(builder, "{");

        parent.append("\r\n")
              .append(HORIZONTAL_DOUBLE_LINE)
              .append(builder)
              .append("\r\n")
              .append(BOTTOM_BORDER);

        return parent.toString();
    }

    private StringBuilder insertNewLine(StringBuilder builder, String symbol) {
        int start = 0, end = builder.length();
        int index;
        while (start < end) {
            index = builder.indexOf(symbol, start);

            if (index == -1) break;

            builder.insert(index + 1, "\r\n║");
            start = index + 1;
        }

        return builder;
    }

    @Override
    public String toString() {

        return reformatToPrint();
    }
}
