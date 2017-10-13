package com.lfkdsk.justel.repl;

import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.compile.compiler.JustCompiler;
import com.lfkdsk.justel.compile.compiler.JustCompilerImpl;
import com.lfkdsk.justel.compile.generate.Generator;
import com.lfkdsk.justel.compile.generate.JavaCodeGenerator;
import com.lfkdsk.justel.exception.ParseException;
import com.lfkdsk.justel.lexer.JustLexerImpl;
import com.lfkdsk.justel.lexer.Lexer;
import com.lfkdsk.justel.parser.JustParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.lfkdsk.justel.token.Token.EOF;

public class JustRepl {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static final String JUST_EL = "JustEL > ";


    /**
     * just-lexer
     */
    static Lexer lexer = new JustLexerImpl(new BufferedReader(new InputStreamReader(System.in)));


    static JustParser parser = new MockParser();

    /**
     * just-compiler
     */
    static JustCompiler compiler = new JustCompilerImpl();

    /**
     * code-generator
     */
    static Generator generator = new JavaCodeGenerator();

    static boolean openAst = false;
    static boolean openMockEval = false;
    static boolean openMockCompile = false;

    public static void main(String[] args) throws Exception {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("");
            System.out.println(ANSI_PURPLE + "Have a nice Day~~" + ANSI_RESET);
            // some cleaning up code...
        }));

        if (args.length < 1) {
            throw new Exception("need more args to use this repl");
        }

        String type = args[0];
        if (type.contains("a")) openAst = true;
        if (type.contains("e")) openMockEval = true;
        if (type.contains("c")) openMockCompile = true;

        run();
    }


    private static String cyanPrint(String msg) {
        return ANSI_CYAN + msg + ANSI_RESET;
    }

    private static void run() {
        try {
            while (lexer.peek(0) != EOF) {
                try {
                    AstNode node = parser.parser(lexer);
                    System.out.println(cyanPrint(String.valueOf(node.getClass())));

                    System.out.println("");
                    System.out.print(cyanPrint(JUST_EL));

                } catch (Exception e) {
                    System.out.println("");
                    System.out.println(cyanPrint(JUST_EL + e.getMessage()));
                    System.out.print(cyanPrint(JUST_EL));
                }
            }
        } catch (ParseException ignore) { }
    }

}
