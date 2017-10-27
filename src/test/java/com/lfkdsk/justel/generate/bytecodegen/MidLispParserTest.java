package com.lfkdsk.justel.generate.bytecodegen;

import com.lfkdsk.justel.ast.tree.AstProgram;
import com.lfkdsk.justel.lexer.JustLexerImpl;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

class MidLispParserTest {

    @Test
    void testMidLisp() {
        JustLexerImpl lexer = new JustLexerImpl(new StringReader("(+ (+ 22222 11111) lfkdsk \"lfkdsk\")"));
        MidLispParser parser = new MidLispParser();
        AstProgram program = (AstProgram) parser.parser(lexer);
    }
}