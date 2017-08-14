package com.lfkdsk.justel.compile.compiler;

import com.lfkdsk.justel.ast.tree.AstProgram;
import com.lfkdsk.justel.compile.generate.Generator;
import com.lfkdsk.justel.compile.generate.JavaCodeGenerator;
import com.lfkdsk.justel.compile.generate.JavaSource;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.context.JustMapContext;
import com.lfkdsk.justel.lexer.JustLexerImpl;
import com.lfkdsk.justel.lexer.Lexer;
import com.lfkdsk.justel.parser.JustParser;
import com.lfkdsk.justel.parser.JustParserImpl;
import com.lfkdsk.justel.utils.logger.Logger;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

/**
 * Created by liufengkai on 2017/8/11.
 */
public class CornerTest {

    @Test
    void ELEngineCornerTest1() {
        compiler("1000+100.0*99-(600-3*15)%(((68-9)-3)*2-100)+10000%7*71", null);
    }


    @Test
    void ELEngineCornerTest2() {
        JustContext vars = new JustMapContext();
        vars.put("i", 100);
        vars.put("pi", 3.14d);
        vars.put("d", -3.9);
        vars.put("b", (byte) 4);
        vars.put("bool", false);
        compiler("i * pi + (d * b - 199) / (1 - d * pi) - (2 + 100 - i / pi) % 99 ==i * pi + (d * b - 199) / (1 - d * pi) - (2 + 100 - i / pi) % 99", vars);
    }

    public static void compiler(String exprStr, JustContext context) {
        Logger.init("gen-code");
        Lexer lexer = new JustLexerImpl(new StringReader(exprStr));
        JustParser parser = new JustParserImpl();
        AstProgram rootNode = null;
        while (lexer.hasMore()) {
            rootNode = (AstProgram) parser.parser(lexer);
        }

        if (rootNode != null && rootNode.isProgramConst()) {
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < 1_0000_0000; i++) {
                rootNode.eval(context);
            }

            System.out.println(System.currentTimeMillis() - startTime);
            return;
        }

        Generator generator = new JavaCodeGenerator(context, rootNode);
        JustCompiler compiler = new JustCompilerImpl();
        JavaSource javaSource = generator.generate();
        com.lfkdsk.justel.eval.Expression expr = compiler.compile(javaSource);

        long startTime = System.currentTimeMillis();
        for (int j = 0; j < 20; j++) {
            for (int i = 0; i < 1_0000_0000; i++) {
                expr.eval(context);
            }
        }
        System.out.println(System.currentTimeMillis() - startTime);
    }

}
