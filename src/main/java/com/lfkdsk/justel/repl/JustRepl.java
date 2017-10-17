package com.lfkdsk.justel.repl;

import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.compile.compiler.JustCompiler;
import com.lfkdsk.justel.compile.compiler.JustCompilerImpl;
import com.lfkdsk.justel.compile.generate.Generator;
import com.lfkdsk.justel.compile.generate.JavaCodeGenerator;
import com.lfkdsk.justel.compile.generate.JavaSource;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.context.JustMapContext;
import com.lfkdsk.justel.lexer.Lexer;
import com.lfkdsk.justel.parser.JustParser;
import jline.console.ConsoleReader;
import org.fusesource.jansi.Ansi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.fusesource.jansi.Ansi.ansi;

public class JustRepl {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String JUST_EL = "JustEL > ";

    /**
     * just-lexer
     */
    private static Lexer lexer = new MockLexer(new BufferedReader(new InputStreamReader(System.in)));


    private static JustParser parser = new MockParser();

    /**
     * just-compiler
     */
    private static JustCompiler compiler = new JustCompilerImpl();

    /**
     * code-generator
     */
    private static Generator generator = new JavaCodeGenerator();

    private static boolean openAst = false;
    private static boolean openMockEval = false;
    private static boolean openMockCompile = false;
    private static boolean openMockGenerate = false;

    private static String logoStr =
            "\n" +
                    "     ██╗██╗   ██╗███████╗████████╗███████╗██╗     \n" +
                    "     ██║██║   ██║██╔════╝╚══██╔══╝██╔════╝██║     \n" +
                    "     ██║██║   ██║███████╗   ██║   █████╗  ██║     \n" +
                    "██   ██║██║   ██║╚════██║   ██║   ██╔══╝  ██║     \n" +
                    "╚█████╔╝╚██████╔╝███████║   ██║   ███████╗███████╗\n" +
                    " ╚════╝  ╚═════╝ ╚══════╝   ╚═╝   ╚══════╝╚══════╝\n" +
                    "                                                  \n";

    public static void main(String[] args) throws Exception {
//        AnsiConsole.systemInstall();

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
        if (type.contains("g")) openMockGenerate = true;
//
        logoStr = logoStr.replace("█", ansi().fg(Ansi.Color.GREEN).a("█").reset().toString());
//        logoStr = logoStr.replace("\\", ansi().fg(Ansi.Color.GREEN).a("\\").reset().toString());
//        logoStr = logoStr.replace("/", ansi().fg(Ansi.Color.GREEN).a("/").reset().toString());
//        logoStr = logoStr.replace("_", ansi().fg(Ansi.Color.GREEN).a("_").reset().toString());
//        logoStr = logoStr.replace("~", ansi().fg(Ansi.Color.GREEN).a("~").reset().toString());

        run();
    }

    private static String cyanPrint(String msg) {
        return ANSI_CYAN + msg + ANSI_RESET;
    }

    private static void run() throws IOException {
        ConsoleReader reader = new ConsoleReader();
        reader.setHistoryEnabled(true);
        System.out.println(logoStr);

        String command;
        JustContext env = new JustMapContext();
        while ((command = reader.readLine(cyanPrint(JUST_EL))) != null) {
            try {

                lexer.reset(command);
                lexer.hasMore();

                AstNode node = parser.parser(lexer);

                if (openAst) {
                    System.out.println(cyanPrint(node.toString()));
                }

                if (openMockEval) {
                    System.out.println(cyanPrint(node.eval(env).toString()));
                }

                if (openMockGenerate) {
                    generator.reset(env, node);

                    JavaSource javaSource = generator.generate();
                    System.out.println(cyanPrint(javaSource.toString()));

                    if (openMockCompile) {
                        long start = System.currentTimeMillis();
                        compiler.compile(javaSource);
                        reader.println("Compile Time :" + (System.currentTimeMillis() - start + " ms"));
                    }
                }

            } catch (Throwable e) {
                System.out.println(cyanPrint(JUST_EL + e.getMessage()));
            }
        }
    }

}
