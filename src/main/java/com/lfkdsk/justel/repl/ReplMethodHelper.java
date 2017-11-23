package com.lfkdsk.justel.repl;

import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.ast.function.ExtendFunctionExpr;
import com.lfkdsk.justel.compile.generate.JavaSource;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.eval.Expression;
import jline.console.completer.StringsCompleter;
import org.fusesource.jansi.AnsiConsole;

import static com.lfkdsk.justel.repl.JustRepl.*;
import static com.lfkdsk.justel.utils.FormatUtils.*;

public class ReplMethodHelper {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String JUST_EL = "JustEL > ";

    static boolean openAst = false;
    static boolean openMockEval = false;
    static boolean openMockCompile = false;
    static boolean openMockGenerate = false;

    ///////////////////////////////////////////////////////////////////////////
    // EXTEND FUNCTIONS
    ///////////////////////////////////////////////////////////////////////////

    public static class EmptyFunction extends ExtendFunctionExpr {

        @Override
        public Object call(Object... params) {
            return null;
        }

        @Override
        public String funcName() {
            return "EMPTY_FUNCTION";
        }
    }

    public static class CompileTest extends ExtendFunctionExpr {

        @Override
        public Object call(Object... params) {
            assert params.length == 2;
            assert params[0] instanceof String;
            assert params[1] instanceof Integer;

            int times = (int) params[1];
            String expr = (String) params[0];

            System.out.println(cyanPrint("Start Compile Test in another thread"));
            executor.execute(() -> {
                long start = System.currentTimeMillis();
                Expression expression = justEL.compile(expr, env);
                for (int i = 0; i < times; i++) {
                    expression.eval(env);
                }
                System.out.println("");
                System.out.println(cyanPrint(beautifulPrint("COMPILE TEST RESULT",
                        "use time :" + (System.currentTimeMillis() - start) + " ms" + " run " + times + " times")));
                System.out.println("");
            });

            return "testing";
        }

        @Override
        public String funcName() {
            return "compileTest";
        }
    }

    public static class EvalTest extends ExtendFunctionExpr {

        @Override
        public Object call(Object... params) {
            assert params.length == 2;
            assert params[0] instanceof String;
            assert params[1] instanceof Integer;
            int times = (int) params[1];
            String expr = (String) params[0];

            System.out.println(cyanPrint("Start Eval Test in another thread"));
            executor.execute(() -> {
                long start = System.currentTimeMillis();
                Expression expression = justEL.expr(expr);
                for (int i = 0; i < times; i++) {
                    expression.eval(env);
                }
                System.out.println("");
                System.out.println(cyanPrint(beautifulPrint("Eval TEST RESULT",
                        "use time :" + (System.currentTimeMillis() - start) + " ms" + " run " + times + " times")));
                System.out.println("");
            });

            return "testing";
        }

        @Override
        public String funcName() {
            return "evalTest";
        }
    }

    public static class GetEnvironment extends ExtendFunctionExpr {

        @Override
        public Object call(Object... params) {
            System.out.println(cyanPrint(contextPrint(env)));
            return env;
        }

        @Override
        public String funcName() {
            return "getEnv";
        }
    }

    public static class Eval extends ExtendFunctionExpr {

        @Override
        public Object call(Object... params) {
            assert params.length == 1;
            String expr = (String) params[0];
            return parser.parser(lexer.scanner(expr)).eval(env).toString();
        }

        @Override
        public String funcName() {
            return "eval";
        }
    }

    public static class AddKey extends ExtendFunctionExpr {

        @Override
        public Object call(Object... params) {
            assert params.length == 1;
            String key = (String) params[0];
            reader.addCompleter(new StringsCompleter(key));
            return key;
        }

        @Override
        public String funcName() {
            return "addKey";
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // FUNCTION HELPER
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Print CYAN Color
     *
     * @param msg msg
     * @return str
     */
    static String cyanPrint(String msg) {
        return ANSI_CYAN + msg + ANSI_RESET;
    }

    /**
     * Resolve Command
     *
     * @param command expr-command
     * @param flag    is + / -
     * @return
     * @see JustRepl#help
     */
    static boolean resolveCommandFlag(String command, boolean flag) {
        boolean result = false;
        if (command.contains("a")) {
            openAst = flag;
            result = true;
        }

        if (command.contains("e")) {
            openMockEval = flag;
            result = true;
        }

        if (command.contains("c")) {
            openMockCompile = flag;
            result = true;
        }

        if (command.contains("g")) {
            openMockGenerate = flag;
            result = true;
        }

        if (command.contains("-flush") && !flag) {
            env.clearVars();
            result = true;
        }

        return result;
    }

    /**
     * Resolve Tag in Command-Line
     *
     * @param command cmd
     * @return result
     */
    static boolean resolveCommandLine(String command) {
        String trimCommand = command.trim();
        if (trimCommand.startsWith("+")) {
            return resolveCommandFlag(trimCommand, true);
        } else if (trimCommand.startsWith("-")) {
            return resolveCommandFlag(trimCommand, false);
        } else {
            return false;
        }
    }

    /**
     * Print AstNode
     *
     * @param node node
     */
    static void runAst(AstNode node) {
        String reformat = reformatAstPrint(node.toString());
        String[] args = {
                "AST ---- Lisp Style",
                insertNewLine(new StringBuilder(reformat), "", "").toString()
        };

        System.out.println(cyanPrint(beautifulPrint(args)));
    }

    /**
     * Print Eval Result
     *
     * @param node ast
     * @param env  env
     */
    static void runEval(AstNode node, JustContext env) {
        long start = System.nanoTime();
        Expression expr = node.expr();
        AnsiConsole.out.println("Eval Expression Time :" + (System.nanoTime() - start + " ns"));

        String reformat = expr.eval(env).toString();

        String[] args = {
                "Value ---- Eval",
                insertNewLine(new StringBuilder(reformat), "\n", "\r\n").toString()
        };

        System.out.println(cyanPrint(beautifulPrint(args)));
    }

    /**
     * Print Compile Result
     *
     * @param node ast
     * @param env  env
     */
    static void runCompile(AstNode node, JustContext env) {

        JavaSource javaSource = generator.generate(env, node);
        System.out.println(cyanPrint(javaSource.toString()));

        if (openMockCompile) {

            long start = System.currentTimeMillis();
            Expression expr = compiler.compile(javaSource);
            AnsiConsole.out.println("Compile Time :" + (System.currentTimeMillis() - start + " ms"));
        }
    }

}
