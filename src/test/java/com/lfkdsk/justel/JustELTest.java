package com.lfkdsk.justel;

import com.lfkdsk.justel.compile.compiler.JustCompilerImpl;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.context.JustMapContext;
import com.lfkdsk.justel.eval.ConstExpression;
import com.lfkdsk.justel.generate.javagen.JavaCodeGenerator;
import com.lfkdsk.justel.lexer.JustLexerImpl;
import com.lfkdsk.justel.parser.JustParserImpl;
import com.lfkdsk.justel.utils.logger.Logger;
import org.junit.jupiter.api.Test;

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

        Logger.i(String.valueOf(new com.lfkdsk.justel.JustEL.Builder()
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
                new com.lfkdsk.justel.JustEL.Builder()
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
                com.lfkdsk.justel.JustEL.builder()
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
        Logger.i(String.valueOf(com.lfkdsk.justel.JustEL.runEval("1111 + lfkdsk + sss", new JustMapContext() {{
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
        Logger.i(String.valueOf(com.lfkdsk.justel.JustEL.runCompile("1111 + lfkdsk + sss", context).eval(context)));
    }


    @Test
    void testJustELBuilder() {
        Logger.init();
        Logger.i(com.lfkdsk.justel.JustEL.builder()
                                         .compiler(code -> new ConstExpression("lfkdsk"))
                                         .create()
                                         .eval("lfkdsk", new JustMapContext() {{
                    put("lfkdsk", 1000);
                }})
                                         .toString());
    }
}