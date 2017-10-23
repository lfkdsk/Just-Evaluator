package com.lfkdsk.justel.generate.bytecodegen;

import com.lfkdsk.justel.ast.tree.AstProgram;
import com.lfkdsk.justel.context.JustMapContext;
import com.lfkdsk.justel.generate.WrapperGenCodeVisitor;
import com.lfkdsk.justel.lexer.JustLexerImpl;
import com.lfkdsk.justel.lexer.Lexer;
import com.lfkdsk.justel.parser.JustParser;
import com.lfkdsk.justel.parser.JustParserImpl;
import com.lfkdsk.justel.utils.logger.Logger;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

class WrapperGenCodeVisitorTest {
    @Test
    void testByteCodeGen() {
        Logger.init("gen-code");
        Lexer lexer = new JustLexerImpl(new StringReader("1 + 2 * 100"));
        JustParser parser = new JustParserImpl();
        AstProgram rootNode = null;
        lexer.hasMore();
        rootNode = (AstProgram) parser.parser(lexer);
        WrapperGenCodeVisitor visitor = new WrapperGenCodeVisitor(new JustMapContext());
        Logger.v(visitor.visitAstProgram(rootNode));
    }
}