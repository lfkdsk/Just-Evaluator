package com.lfkdsk.justel.utils;

public class FormatUtils {
    private FormatUtils() {
    }

    private static final char TOP_LEFT_CORNER = '╔';
    private static final char BOTTOM_LEFT_CORNER = '╚';
    private static final char MIDDLE_CORNER = '╟';
    private static final char HORIZONTAL_DOUBLE_LINE = '║';
    private static final String DOUBLE_DIVIDER = "════════════════════════════════════════════";
    private static final String SINGLE_DIVIDER = "────────────────────────────────────────────";
    private static final String TOP_BORDER = TOP_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    private static final String BOTTOM_BORDER = BOTTOM_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    private static final String MIDDLE_BORDER = MIDDLE_CORNER + SINGLE_DIVIDER + SINGLE_DIVIDER;

    /**
     * Reformat Mid-Code(Lisp-Style)
     *
     * @param msg Lisp-Style-Code ep: (+ 1 2)
     * @return format code
     * （+
     * 1
     * 1
     * )
     */
    public static String reformatAstPrint(String msg) {
        StringBuilder builder = new StringBuilder();
        int level = 0;
        for (Character character : msg.substring(1, msg.length() - 1)
                                      .toCharArray()) {
            switch (character) {
                case '(': {
                    builder.append('(');
                    level++;
                    break;
                }
                case ')': {
                    level--;
                    builder.append('\n');
                    for (int i = 0; i < level; i++) {
                        builder.append('\t');
                    }
                    builder.append(')');
                    break;
                }
                case ' ': {
                    builder.append('\n');
                    for (int i = 0; i < level; i++) {
                        builder.append('\t');
                    }
                    break;
                }
                default: {
                    builder.append(character);
                }
            }
        }

        return builder.toString();
    }

    public static StringBuilder insertNewLine(StringBuilder builder, String symbol, String insertSymbol) {
        int start = 0, end = builder.length();
        int index;
        while (start < end) {
            index = builder.indexOf(symbol, start);

            if (index == -1) break;

            builder.insert(index + 1, insertSymbol);
            start = index + 1;
        }

        return builder;
    }

    public static String beautifulPrint(String... lines) {
        StringBuilder parent = new StringBuilder();
        parent.append(TOP_BORDER);
        for (int i = 0; i < lines.length; i++) {
            String arg = lines[i];
            if (i == lines.length - 1) {
                for (String line : arg.split("\\r?\\n")) {
                    parent.append("\r")
                          .append(HORIZONTAL_DOUBLE_LINE)
                          .append(line)
                          .append("\t\n");
                }

                parent.append("\r")
                      .append(BOTTOM_BORDER);
                break;
            }

            for (String line : arg.split("\\r?\\n")) {
                parent.append("\t\n")
                      .append(HORIZONTAL_DOUBLE_LINE)
                      .append(line).append("\t\n");
            }

            parent.append(MIDDLE_BORDER);
        }

        return parent.toString();
    }
}
