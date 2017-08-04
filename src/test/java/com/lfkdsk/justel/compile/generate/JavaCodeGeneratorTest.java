/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.compile.generate;

import com.lfkdsk.justel.ast.base.AstLeaf;
import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.compile.compiler.JustCompiler;
import com.lfkdsk.justel.compile.compiler.JustCompilerImpl;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.context.JustMapContext;
import com.lfkdsk.justel.eval.Expression;
import com.lfkdsk.justel.lexer.JustLexerImpl;
import com.lfkdsk.justel.lexer.Lexer;
import com.lfkdsk.justel.parser.JustParser;
import com.lfkdsk.justel.parser.JustParserImpl;
import com.lfkdsk.justel.token.SepToken;
import com.lfkdsk.justel.utils.logger.Logger;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

/**
 * Created by liufengkai on 2017/8/4.
 */
public class JavaCodeGeneratorTest {

    @Test
    void testJavaCodeGenerator() {
        JustContext context = new JustMapContext();
        context.put("lfkdsk", 10e2);
        context.put("pi", 3.14);
        Generator generator = new JavaCodeGenerator(context, new AstLeaf(SepToken.AND_TOKEN));
        Logger.init("gen-code");
        Logger.i(generator.generate().toString());
    }

    @Test
    void testJavaCodeCompiler() {
        JustContext context = new JustMapContext();
        context.put("lfkdsk", 10e2);
        context.put("pi", 3.14);

        Lexer lexer = new JustLexerImpl(new StringReader("lfkdsk * pi * lfkdsk * pi"));
//        Lexer lexer = new JustLexerImpl(new StringReader("lfkdsk * pi"));
        JustParser parser = new JustParserImpl();
        AstNode rootNode = null;
        while (lexer.hasMore()) {
            rootNode = parser.parser(lexer);
        }


        Generator generator = new JavaCodeGenerator(context, rootNode);
        JustCompiler compiler = new JustCompilerImpl();

        Logger.init("gen-code");
        JavaSource javaSource = generator.generate();
        Logger.i(javaSource.toString());
        Expression expr = compiler.compile(javaSource);
        Logger.i(expr.eval(context).toString());
        Logger.v(rootNode.eval(context).toString());
        Logger.i(expr.toString());
    }

    public static void compiler(String exprStr, JustContext context) {
        Logger.init("gen-code");
        Lexer lexer = new JustLexerImpl(new StringReader(exprStr));
        JustParser parser = new JustParserImpl();
        AstNode rootNode = null;
        while (lexer.hasMore()) {
            rootNode = parser.parser(lexer);
        }
        Generator generator = new JavaCodeGenerator(context, rootNode);
        JustCompiler compiler = new JustCompilerImpl();
        JavaSource javaSource = generator.generate();
        Expression expr = compiler.compile(javaSource);
        Logger.i(expr.eval(context).toString());
    }
}