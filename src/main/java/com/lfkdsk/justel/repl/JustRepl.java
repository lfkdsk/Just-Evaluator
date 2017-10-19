package com.lfkdsk.justel.repl;

import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.compile.compiler.JustCompiler;
import com.lfkdsk.justel.compile.compiler.JustCompilerImpl;
import com.lfkdsk.justel.generate.Generator;
import com.lfkdsk.justel.generate.javagen.JavaCodeGenerator;
import com.lfkdsk.justel.generate.javagen.JavaSource;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.context.JustMapContext;
import com.lfkdsk.justel.eval.Expression;
import com.lfkdsk.justel.lexer.Lexer;
import com.lfkdsk.justel.parser.JustParser;
import jline.console.ConsoleReader;
import jline.console.history.FileHistory;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.lfkdsk.justel.utils.FormatUtils.*;
import static org.fusesource.jansi.Ansi.ansi;

/**
 * JustEL Debug Tools
 * "     ██╗██╗   ██╗███████╗████████╗   ███████╗██╗
 * "     ██║██║   ██║██╔════╝╚══██╔══╝   ██╔════╝██║
 * "     ██║██║   ██║███████╗   ██║█████╗█████╗  ██║
 * "██   ██║██║   ██║╚════██║   ██║╚════╝██╔══╝  ██║
 * "╚█████╔╝╚██████╔╝███████║   ██║      ███████╗███████╗
 * " ╚════╝  ╚═════╝ ╚══════╝   ╚═╝      ╚══════╝╚══════╝
 * "*@author liufengkai
 */
public class JustRepl {
    static String logoStr =
            "\n" +
                    "     ██╗██╗   ██╗███████╗████████╗   ███████╗██╗     \n" +
                    "     ██║██║   ██║██╔════╝╚══██╔══╝   ██╔════╝██║     \n" +
                    "     ██║██║   ██║███████╗   ██║█████╗█████╗  ██║     \n" +
                    "██   ██║██║   ██║╚════██║   ██║╚════╝██╔══╝  ██║     \n" +
                    "╚█████╔╝╚██████╔╝███████║   ██║      ███████╗███████╗\n" +
                    " ╚════╝  ╚═════╝ ╚══════╝   ╚═╝      ╚══════╝╚══════╝\n" +
                    "                                                     \n";

    private static final String help =
            " -a show ast structure of this expr \n" +
                    " -e eval this expr \n" +
                    " -g generate java source code \n" +
                    " -c compile java source code [need -g] \n" +
                    " -s stressed test of this expr\n" +
                    " Just-REPL support assign(=) operator to set id-token's value, this grammar \n " +
                    "won't support in JustEL";

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String JUST_EL = "JustEL > ";

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

    private static JustContext env = new JustMapContext();
    private static boolean openAst = false;
    private static boolean openMockEval = false;
    private static boolean openMockCompile = false;
    private static boolean openMockGenerate = false;
    private static boolean openStressedTest = false;

    private static String cyanPrint(String msg) {
        return ANSI_CYAN + msg + ANSI_RESET;
    }

    private static void resolveCommandFlag(String command, boolean flag) {
        if (command.contains("a")) openAst = flag;
        if (command.contains("e")) openMockEval = flag;
        if (command.contains("c")) openMockCompile = flag;
        if (command.contains("g")) openMockGenerate = flag;
        if (command.contains("s")) openStressedTest = flag;
        if (command.contains("-flush") && !flag) env.clearVars();
    }

    private static boolean resolveCommandLine(String command) {
        String trimCommand = command.trim();
        if (trimCommand.startsWith("+")) {
            resolveCommandFlag(trimCommand, true);
        } else if (trimCommand.startsWith("-")) {
            resolveCommandFlag(trimCommand, false);
        } else {
            return false;
        }

        return true;
    }

    private static void runAst(AstNode node) {
        String reformat = reformatAstPrint(node.toString());
        String[] args = {
                "AST ---- Lisp Style",
                insertNewLine(new StringBuilder(reformat), "\n", "║").toString()
        };

        System.out.println(cyanPrint(beautifulPrint(args)));
    }

    private static void runEval(AstNode node, JustContext env) {
        long start = System.nanoTime();
        Expression expr = node.expr();
        AnsiConsole.out.println("Eval Expression Time :" + (System.nanoTime() - start + " ns"));

        String reformat = expr.eval(env).toString();

        String[] args = {
                "Value ---- Eval",
                insertNewLine(new StringBuilder(reformat), "\n", "\r\n║").toString()
        };

        System.out.println(cyanPrint(beautifulPrint(args)));

        if (openStressedTest) {

            start = System.currentTimeMillis();

            for (int i = 0; i < 1_0000; i++) {
                expr.eval(env);
            }

            AnsiConsole.out.println("Run Eval 1_0000 Time :" + (System.currentTimeMillis() - start + " ms"));
        }
    }

    private static void runCompile(AstNode node, JustContext env) {
        generator.reset(env, node);

        JavaSource javaSource = generator.generate();
        System.out.println(cyanPrint(javaSource.toString()));

        if (openMockCompile) {

            long start = System.currentTimeMillis();
            Expression expr = compiler.compile(javaSource);
            AnsiConsole.out.println("Compile Time :" + (System.currentTimeMillis() - start + " ms"));

            if (openStressedTest) {

                start = System.currentTimeMillis();

                for (int i = 0; i < 1_0000; i++) {
                    expr.eval(env);
                }

                AnsiConsole.out.println("Run 1_0000 Time :" + (System.currentTimeMillis() - start + " ms"));
            }
        }
    }

    private static void run() throws IOException {
        ConsoleReader reader = new ConsoleReader();

        FileHistory fileHistory = new FileHistory(new File("./sh/history.just"));
        reader.setHistoryEnabled(true);
        reader.setHistory(fileHistory);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("");
            System.out.println(ANSI_PURPLE + "Have a nice Day~~" + ANSI_RESET);
            try {
                fileHistory.flush();
            } catch (IOException ignored) { }
        }));

        String command;
        while ((command = reader.readLine(cyanPrint(JUST_EL))) != null) {

            if (command.equals("")) continue;
            else if (command.trim().equals("-q")) return;
            else if (resolveCommandLine(command)) continue;

            try {

                lexer.reset(command);
                lexer.hasMore();

                AstNode node = parser.parser(lexer);

                if (openAst) {
                    runAst(node);
                }

                if (openMockEval) {
                    runEval(node, env);
                }

                if (openMockGenerate) {
                    runCompile(node, env);
                }

            } catch (Throwable e) {
                AnsiConsole.out.println(ansi().fgRed().a(JUST_EL + e.getMessage()).reset().toString());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        AnsiConsole.systemInstall();
        logoStr = logoStr.replace("█", ansi().fg(Ansi.Color.GREEN).a("█").reset().toString());

        System.out.println(ansi().eraseScreen().render(logoStr));
        System.out.println(ANSI_PURPLE + "Welcome to JustEL Debug Tools~~" + ANSI_RESET);

        System.out.println(ansi().fgBrightGreen().render(help).reset().toString());
        if (args.length < 1) {
            System.out.println(ansi().fgBrightRed().render("need more args").reset().toString());
            return;
        }

        String type = args[0];
        resolveCommandFlag(type, true);

        run();
    }

}
