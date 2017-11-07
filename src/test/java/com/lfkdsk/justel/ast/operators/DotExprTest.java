/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.ast.operators;

import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.context.JustMapContext;
import com.lfkdsk.justel.utils.logger.Logger;
import org.junit.jupiter.api.Test;

import static com.lfkdsk.justel.compile.generate.JavaCodeGeneratorTest.compiler;
import static com.lfkdsk.justel.parser.JustParserImplTest.runExpr;

/**
 * Created by liufengkai on 2017/8/2.
 */
public class DotExprTest {

    public class O {
        private String ffff = "sss";

        public class F {
            String llll = "fff";

            public String getLlll() {
                return llll;
            }

            public String hello(String ff) {
                return " hello " + ff;
            }
        }

        private F f = new F();

        public F getF() {
            return f;
        }

        public String lfk() {
            return "fffffff";
        }

        public String lfkdsk(String val) {
            return val;
        }
    }

    @Test
    void testDotExpr() {
        JustContext context = new JustMapContext();
        context.put("lfkdsk", new O());
        runExpr("lfkdsk.lfk()", true, context);
    }

    @Test
    void testDotValueExpr() {
        JustContext context = new JustMapContext();
        context.put("lfkdsk", new O());
        runExpr("lfkdsk.ffff", true, context);
    }

    @Test
    void testDotManyExpr() {
        JustContext context = new JustMapContext();
        context.put("lfkdsk", new O());
        runExpr("lfkdsk.lfkdsk(\"1111111\")", true, context);
    }

    @Test
    void testDotExprCompiler() {
        JustContext context = new JustMapContext();
        context.put("lfkdsk", new O());
        Logger.init();
        Logger.i(compiler("lfkdsk.lfkdsk(\"1111111\")", context));
    }

    @Test
    void testDotEvalDouble() {
        JustContext context = new JustMapContext();
        context.put("lfkdsk", new O());
        Logger.init();
        Logger.i(compiler("lfkdsk.f.llll", context));
    }

    @Test
    void testDotEvalThird() {
        JustContext context = new JustMapContext();
        context.put("lfkdsk", new O());
        Logger.init();
        Logger.i(runExpr("lfkdsk.f.hello(\"lfkdsk\")", true, context));
    }
}