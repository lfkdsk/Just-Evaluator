package com.lfkdsk.justel.repl;

import com.lfkdsk.justel.ast.function.ExtendFunctionExpr;
import com.lfkdsk.justel.eval.Expression;

import static com.lfkdsk.justel.repl.JustRepl.*;
import static com.lfkdsk.justel.utils.FormatUtils.beautifulPrint;
import static com.lfkdsk.justel.utils.FormatUtils.contextPrint;

public class ReplMethodHelper {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String JUST_EL = "JustEL > ";

    static String cyanPrint(String msg) {
        return ANSI_CYAN + msg + ANSI_RESET;
    }

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
                System.out.println(cyanPrint(beautifulPrint("use time :" + (System.currentTimeMillis() - start) + " ms" + " run " + times + " times")));
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
                System.out.println(cyanPrint(beautifulPrint("use time :" + (System.currentTimeMillis() - start) + " ms" + " run " + times + " times")));
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
}
