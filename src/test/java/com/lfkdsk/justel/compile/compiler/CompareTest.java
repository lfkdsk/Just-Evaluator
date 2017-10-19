package com.lfkdsk.justel.compile.compiler;

import com.lfkdsk.justel.ast.tree.AstProgram;
import com.lfkdsk.justel.generate.Generator;
import com.lfkdsk.justel.generate.javagen.JavaCodeGenerator;
import com.lfkdsk.justel.generate.javagen.JavaSource;
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
public class CompareTest {

    @Test
    void ELEngineCornerTest1() {
        compiler("1000+100.0*99-(600-3*15)%(((68-9)-3)*2-100)+10000%7*71", null);
    }


    @Test
    void ELEngineCornerTest3() {
        JustContext vars = new JustMapContext();
        vars.put("f", 100);
        compiler("f+1000+100.0*99-(600-3*15)%(((68-9)-3)*2-100)+10000%7*71+f", vars);
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

    @Test
    void ELEngineCornerTest4() {
        JustContext vars = new JustMapContext();
        vars.put("i", 100);
        vars.put("pi", 3.14d);
        vars.put("d", -3.9);
        vars.put("b", (byte) 4);
        vars.put("bool", false);
        compiler("i * pi + (d * b - 109) / (1 - d * pi) - (2 + 100 - i / pi) % 99 " +
                "== i * pi  + (d * b - 119) / (1 - d * pi) - (2 + 100 - i / pi) % 99" +
                "|| 1111 * pi * pi " +
                "== i * pi + (d * b - 119) / (1 - d * pi) - (20 + 100 - i / pi) % 909" +
                "|| i * pi + (d * b - 189) / (1 - d * pi) - (21 + 100 - i / pi) % 99" +
                "== 1111 * pi * pi + 1111 * pi * pi " +
                "|| i * pi + (d * b - 179) / (1 - d * pi) - (12 + 100 - i / pi) % 99" +
                "== i * pi + (d * b - 189) / (1 - d * pi) - (12 + 100 - i / pi) % 99" +
                "|| 122 + 1111 * pi * pi " +
                "== i * pi + (d * b - 199) / (1 - d * pi) - (32 + 100 - i / pi) % 99", vars);
    }

    public static long compiler(String exprStr, JustContext context) {
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

            long time = System.currentTimeMillis() - startTime;

            System.out.println(time);

            return time;
        }

        Generator generator = new JavaCodeGenerator(context, rootNode);
        JustCompiler compiler = new JustCompilerImpl();
        JavaSource javaSource = generator.generate();
//        Logger.v(javaSource.toString());
        com.lfkdsk.justel.eval.Expression expr = compiler.compile(javaSource);

        long startTime = System.currentTimeMillis();
//        for (int j = 0; j < 20; j++) {
        for (int i = 0; i < 1_0000_0000; i++) {
            expr.eval(context);
        }
//        }
        long time = System.currentTimeMillis() - startTime;

        System.out.println(time);
        return time;
    }
}
