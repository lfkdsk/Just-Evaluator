package com.lfkdsk.justel.repl;

import com.lfkdsk.justel.JustEL;
import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.compile.compiler.JustCompiler;
import com.lfkdsk.justel.compile.compiler.JustCompilerImpl;
import com.lfkdsk.justel.compile.generate.Generator;
import com.lfkdsk.justel.context.JustArrayContext;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.lexer.Lexer;
import com.lfkdsk.justel.parser.JustParser;
import com.lfkdsk.justel.token.Token;
import com.lfkdsk.justel.utils.logger.Logger;
import jline.console.ConsoleReader;
import jline.console.completer.StringsCompleter;
import jline.console.history.FileHistory;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.lfkdsk.justel.repl.ReplMethodHelper.*;
import static org.fusesource.jansi.Ansi.ansi;

/**
 * JustEL Debug Tools
 * "     ██╗██╗   ██╗███████╗████████╗   ███████╗██╗
 * "     ██║██║   ██║██╔════╝╚══██╔══╝   ██╔════╝██║
 * "     ██║██║   ██║███████╗   ██║█████╗█████╗  ██║
 * "██   ██║██║   ██║╚════██║   ██║╚════╝██╔══╝  ██║
 * "╚█████╔╝╚██████╔╝███████║   ██║      ███████╗███████╗
 * " ╚════╝  ╚═════╝ ╚══════╝   ╚═╝      ╚══════╝╚══════╝
 * "
 *
 * @author liufengkai
 * @see ReplMethodHelper
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
                    " -flush clear all environment var \n" +
                    " Just-REPL support assign(=) operator to set id-token's value, this grammar \n " +
                    "won't support in JustEL";

    static Lexer lexer = new MockLexer(new BufferedReader(new InputStreamReader(System.in)));

    static JustParser parser = new MockParser();

    static JustCompiler compiler = new JustCompilerImpl();

    static Generator generator = new MockGenerator();

    static JustEL justEL = JustEL.builder()
                                 .lexer(lexer)
                                 .parser(parser)
                                 .compiler(compiler)
                                 .create();

    static ExecutorService executor = Executors.newFixedThreadPool(10);

    static JustContext env = new JustArrayContext() {{
        putExtendFunc(new CompileTest());
        putExtendFunc(new EvalTest());
        putExtendFunc(new GetEnvironment());
        putExtendFunc(new Eval());
        putExtendFunc(new AddKey());
    }};

    static ConsoleReader reader = null;

    private static void run() throws IOException {
        reader = new ConsoleReader();
        reader.addCompleter(new StringsCompleter("compileTest", "evalTest", "-acge", "getEnv()", "eval", "addKey"));

        FileHistory fileHistory = new FileHistory(new File("./sh/history.just"));
        reader.setHistoryEnabled(true);
        reader.setHistory(fileHistory);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("");
            System.out.println(ANSI_PURPLE + "Have a nice Day~~" + ANSI_RESET);
            try {
                fileHistory.flush();
            } catch (IOException ignored) {
            }
        }));

        String command;
        while ((command = reader.readLine(cyanPrint(JUST_EL))) != null) {

            if (command.equals("")) continue;
            else if (command.trim().equals("-q")) return;
            else if (resolveCommandLine(command)) continue;

            try {

                Queue<Token> tokens = lexer.scanner(command);

                AstNode node = parser.parser(tokens);

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

        Logger.init();

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

        // close executor
        executor.shutdown();
    }

}
