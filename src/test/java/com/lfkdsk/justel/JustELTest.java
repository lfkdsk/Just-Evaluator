package com.lfkdsk.justel;

import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.compile.compiler.JustCompiler;
import com.lfkdsk.justel.compile.compiler.JustCompilerImpl;
import com.lfkdsk.justel.compile.generate.Generator;
import com.lfkdsk.justel.compile.generate.JavaCodeGenerator;
import com.lfkdsk.justel.compile.generate.JavaSource;
import com.lfkdsk.justel.context.JustArrayContext;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.context.JustMapContext;
import com.lfkdsk.justel.eval.Expression;
import com.lfkdsk.justel.lexer.JustLexerImpl;
import com.lfkdsk.justel.lexer.Lexer;
import com.lfkdsk.justel.parser.JustParser;
import com.lfkdsk.justel.parser.JustParserImpl;
import com.lfkdsk.justel.utils.logger.Logger;
import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

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
//        Logger.i(JustEL.builder()
//                       .compiler(code -> new ConstExpression("lfkdsk"))
//                       .create()
//                       .eval("lfkdsk", new JustMapContext() {{
//                           put("lfkdsk", 1000);
//                       }})
//                       .toString());

//        JustEL.builder()
//              .parser(lexer -> {
//              })
//              .compiler(code -> {
//              })
//              .generator((context, rootNode) -> {
//              });

        Supplier<Generator> generator = JavaCodeGenerator::new;
        Supplier<JustCompiler> compiler = JustCompilerImpl::new;
        Supplier<JustParser> parser = JustParserImpl::new;
        Function<Lexer, AstNode> parserCode = (Lexer lexer) -> parser.get().parser(lexer);
        BiFunction<JustContext, AstNode, JavaSource> generateCode = (JustContext context, AstNode root) -> generator.get().generate(context, root);
        Function<JavaSource, Expression> compileCode = (JavaSource code) -> compiler.get().compile(code);
        compileCode.apply(
                generateCode.apply(
                        new JustArrayContext() {{ put("lfkdsk", 111111); }},
                        parserCode.apply(
                                new JustLexerImpl("lfkdsk"))))
                   .eval(new JustArrayContext() {{ put("lfkdsk", 11111); }});
    }
}