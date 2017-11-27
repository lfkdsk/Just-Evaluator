package com.lfkdsk.justel;

import com.lfkdsk.justel.compile.compiler.JustCompilerImpl;
import com.lfkdsk.justel.compile.generate.JavaCodeGenerator;
import com.lfkdsk.justel.context.JustArrayContext;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.context.JustMapContext;
import com.lfkdsk.justel.lexer.JustLexerImpl;
import com.lfkdsk.justel.parser.JustParserImpl;
import com.lfkdsk.justel.utils.logger.Logger;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static com.lfkdsk.justel.eval.ExprBinder.of;

/**
 * Created by liufengkai on 2017/8/23.
 */
class JustELTest {
    @Test
    void eval() {
        Logger.init();
        JustContext context = new JustMapContext() {{
            put("sss", 1111);
            put("lfkdsk", "sss");
        }};

        Logger.i(String.valueOf(new JustEL.Builder()
                .lexer(new JustLexerImpl())
                .parser(new JustParserImpl())
                .compiler(new JustCompilerImpl())
                .generator(new JavaCodeGenerator())
                .create()
                .eval("1111 + lfkdsk + sss", context)
        ));
    }

    @Test
    void evalExpr() {
        Logger.init();
        JustContext context = new JustMapContext() {{
            put("sss", 1111);
            put("lfkdsk", "sss");
        }};

        Logger.i(String.valueOf(
                new JustEL.Builder()
                        .lexer(new JustLexerImpl())
                        .parser(new JustParserImpl())
                        .compiler(new JustCompilerImpl())
                        .generator(new JavaCodeGenerator())
                        .create()
                        .expr("1111 + lfkdsk + sss")
                        .eval(context)
        ));
    }

    @Test
    void compile() {
        Logger.init();
        JustContext context = new JustMapContext() {{
            put("sss", 1111);
            put("lfkdsk", "sss");
        }};

        Logger.i(String.valueOf(
                JustEL.builder()
                      .lexer(new JustLexerImpl())
                      .parser(new JustParserImpl())
                      .compiler(new JustCompilerImpl())
                      .generator(new JavaCodeGenerator())
                      .create()
                      .compile("1111 + lfkdsk + sss", context)
                      .eval(context)
        ));
    }

    @Test
    void runEval() {
        Logger.init();
        Logger.i(String.valueOf(JustEL.runEval("1111 + lfkdsk + sss", new JustMapContext() {{
            put("sss", 1111);
            put("lfkdsk", "sss");
        }})));
    }

    @Test
    void runCompile() {
        Logger.init();
        JustContext context = new JustMapContext() {{
            put("sss", 1111);
            put("lfkdsk", "sss");
        }};
        Logger.i(String.valueOf(JustEL.runCompile("1111 + lfkdsk + sss", context).eval(context)));
    }


    @Test
    void testJustELBuilder() {
        Logger.init();
        JustEL.builder()
              .create()
              .exprs(
                      of(new JustArrayContext() {{
                          put("lfkdsk", 10000);
                      }}, "lfkdsk + 10000000"),
                      of(new JustArrayContext() {{
                          put("lfkdsk", 10001);
                      }}, "lfkdsk + 10000000"),
                      of(new JustArrayContext() {{
                          put("lfkdsk", 11001);
                      }}, "lfkdsk + 10000000"),
                      of(new JustArrayContext() {{
                          put("lfkdsk", 11000);
                      }}, "lfkdsk + 10000000"))
              .collect(Collectors.toList())
              .forEach(System.out::println);
    }
}