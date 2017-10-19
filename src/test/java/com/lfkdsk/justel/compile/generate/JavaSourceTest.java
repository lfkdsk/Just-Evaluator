package com.lfkdsk.justel.compile.generate;

import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.ast.function.ExtendFunctionExprTest;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.context.JustMapContext;
import com.lfkdsk.justel.generate.Generator;
import com.lfkdsk.justel.generate.javagen.JavaCodeGenerator;
import com.lfkdsk.justel.generate.javagen.JavaSource;
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
class JavaSourceTest {

    @Test
    void testReFormatSourceCode() {
        Logger.init();
        ExtendFunctionExprTest.ExtendFunc func = new ExtendFunctionExprTest.ExtendFunc();
        JustContext context = new JustMapContext();
        context.putExtendFunc(func.funcName(), func);

        Logger.init("gen-code");
        Lexer lexer = new JustLexerImpl(new StringReader("add(111,222)"));
        JustParser parser = new JustParserImpl();
        AstNode rootNode = null;
        while (lexer.hasMore()) {
            rootNode = parser.parser(lexer);
        }
        Generator generator = new JavaCodeGenerator(context, rootNode);
        JavaSource javaSource = generator.generate();

        Logger.i(javaSource.toString());
    }
}