package com.lfkdsk.justel.compile.generate;

import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.ast.function.ExtendFunctionExprTest;
import com.lfkdsk.justel.ast.tree.AstFuncExpr;
import com.lfkdsk.justel.compile.compiler.JustCompiler;
import com.lfkdsk.justel.compile.compiler.JustCompilerImpl;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.context.JustMapContext;
import com.lfkdsk.justel.eval.Expression;
import com.lfkdsk.justel.lexer.JustLexerImpl;
import com.lfkdsk.justel.lexer.Lexer;
import com.lfkdsk.justel.parser.JustParser;
import com.lfkdsk.justel.parser.JustParserImpl;
import com.lfkdsk.justel.utils.logger.Logger;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static com.lfkdsk.justel.compile.generate.JavaCodeGeneratorTest.compiler;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by liufengkai on 2017/8/11.
 */
class JavaSourceTest {

    @Test
    void testReFormatSourceCode() {
        Logger.init();
        ExtendFunctionExprTest.ExtendFunc func = new ExtendFunctionExprTest.ExtendFunc();
        AstFuncExpr.extFunc.put(func.functionName(), func);
        JustContext context = new JustMapContext();


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