package com.lfkdsk.justel.utils;

import com.lfkdsk.justel.ast.function.ExtendFunctionExpr;
import com.lfkdsk.justel.context.JustArrayContext;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.repl.ReplMethodHelper;
import com.lfkdsk.justel.utils.table.Block;
import com.lfkdsk.justel.utils.table.Board;
import com.lfkdsk.justel.utils.table.Table;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class FormatUtils {
    private FormatUtils() {
    }

    private static final char TOP_LEFT_CORNER = '╔';
    private static final char BOTTOM_LEFT_CORNER = '╚';
    private static final char MIDDLE_CORNER = '╟';
    private static final char HORIZONTAL_DOUBLE_LINE = '║';
    private static final String DOUBLE_DIVIDER = "═══════════════════════════════════════════════════════════════";
    private static final String SINGLE_DIVIDER = "───────────────────────────────────────────────────────────────";
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

                parent.append("\r").append(BOTTOM_BORDER);
                break;
            }

            for (String line : arg.split("\\r?\\n")) {
                if (i > 0) {
                    parent.append("\r");
                } else {
                    parent.append("\r\n");
                }

                parent.append(HORIZONTAL_DOUBLE_LINE)
                      .append(line)
                      .append("\t\n");
            }

            parent.append(MIDDLE_BORDER).append("\n");
        }

        return parent.toString();
    }

    public static String contextPrint(JustContext context) {
        if (context.varsKeySet().isEmpty()) {
            context.put("EMPTY_CONTEXT", new Object());
        } else {
            context.remove("EMPTY_CONTEXT");
        }

        final int valueOriginSize = context.varsKeySet().size();
        final int valueSize = valueOriginSize <= 1 ? 3 : valueOriginSize * 4 - 1;
        final int funcsOriginSize = context.functions().size();
        final int funcsSize = funcsOriginSize <= 1 ? 3 : funcsOriginSize * 4 - 1;
        final int globalBoardSize = 175;

        Board board = new Board(globalBoardSize);
        Block sdtBlock = new Block(board, 9, 5, "CONTEXT\nDETAILS\nTABLE");
        board.setInitialBlock(sdtBlock.setDataAlign(Block.DATA_CENTER));

        Block keyBlock = new Block(board, 16, 3, "KEY");
        sdtBlock.setRightBlock(keyBlock.setDataAlign(Block.DATA_CENTER));

        Block valueBlock = new Block(board, 98, 3, "VALUE");
        keyBlock.setRightBlock(valueBlock.setDataAlign(Block.DATA_CENTER));

        Block kvTableBlock = new Block(board, 9, valueSize, "K-V\nTABLE");
        sdtBlock.setBelowBlock(kvTableBlock.setDataAlign(Block.DATA_CENTER));

        Block extendFunc = new Block(board, 9, funcsSize + 2, "Extend\nFunction");
        kvTableBlock.setBelowBlock(extendFunc.setDataAlign(Block.DATA_CENTER));


        Block totalBlock = new Block(board, 26, 3, "TOTAL");
        extendFunc.setBelowBlock(totalBlock.setDataAlign(Block.DATA_CENTER));

        List<String> subjects = Arrays.asList("Name", "Index", "Type", "Value");
        List<List<String>> figures =
                context.varsKeySet()
                       .stream()
                       .map(key -> {
                           Object o = context.get(key);
                           return Arrays.asList(key,
                                   context instanceof JustArrayContext
                                           ? String.valueOf(context.indexOf(key))
                                           : "DON'T SUPPORT",
                                   o.getClass().getCanonicalName(),
                                   o.toString());
                       })
                       .collect(toList());

        List<Integer> figuresColWidthsList = Arrays.asList(16, 32, 32, 32);
        Table figuresTable = new Table(board, 32, subjects, figures, figuresColWidthsList);
        figuresTable.setGridMode(Table.GRID_FULL);
        figuresTable.setHeaderHeight(1);
        figuresTable.setRowHeight(3);
        board.appendTableTo(1, Board.APPEND_BELOW, figuresTable);

        if (context.functions().isEmpty()) {
            context.putExtendFunc(new ReplMethodHelper.EmptyFunction());
        }

        List<List<String>> funcs = context.functions()
                                          .stream()
                                          .map((key) -> {
                                              ExtendFunctionExpr expr = context.getExtendFunc(key);
                                              return Arrays.asList(key, "DON'T SUPPORT", expr.getClass().getCanonicalName(), expr.toString());
                                          }).collect(toList());


        List<Integer> funcsLists = Arrays.asList(16, 32, 32, 32);
        Table funcsTable = new Table(board, 32, subjects, funcs, funcsLists);
        funcsTable.setGridMode(Table.GRID_FULL);
        funcsTable.setHeaderHeight(1);
        funcsTable.setRowHeight(3);
        board.appendTableTo(4, Board.APPEND_RIGHT, funcsTable);
        MemoryCounter counter = new MemoryCounter();
        String allBuilder = "CONTEXT :" +
                valueOriginSize +
                " INNER ARGUMENTS | " +
                funcsOriginSize +
                " INNER FUNCTIONS | " +
                " USED " +
                counter.estimate(context) +
                "K MEMORY";

        Block messageBlock = new Block(board, 98, 3, allBuilder);
        totalBlock.setRightBlock(messageBlock.setDataAlign(Block.DATA_CENTER));

        return board.invalidate().build().getPreview();
    }
}
