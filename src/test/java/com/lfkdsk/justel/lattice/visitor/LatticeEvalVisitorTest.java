package com.lfkdsk.justel.lattice.visitor;

import com.lfkdsk.justel.ast.tree.AstProgram;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.context.JustMapContext;
import com.lfkdsk.justel.lattice.LatticeParserImpl;
import com.lfkdsk.justel.lexer.JustLexerImpl;
import com.lfkdsk.justel.lexer.Lexer;
import com.lfkdsk.justel.parser.JustParser;
import com.lfkdsk.justel.utils.logger.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LatticeEvalVisitorTest {

    LatticeEvalVisitor visitor;

    JustContext context;

    Lexer lexer;

    JustParser parser;

    static {
        Logger.init();
    }

    @BeforeEach
    void setUp() {
        lexer = new JustLexerImpl();
        parser = new LatticeParserImpl();
        context = new JustMapContext();
        visitor = new LatticeEvalVisitor(context);
    }

    ///////////////////////////////////////////////////////////////////////////
    // "xxx.xxx" meta - circle - eval
    ///////////////////////////////////////////////////////////////////////////

    class Hello {

        private long lfkdsk = 10000;

        public long getLfkdsk() {
            return lfkdsk;
        }

        @Override
        public String toString() {
            return "Hello World";
        }
    }

    @Test
    void testStringEval() {
        context.put("hello", new Hello());
        lexer.reset("hello.lfkdsk");
        lexer.hasMore();

        AstProgram node = (AstProgram) parser.parser(lexer);
        Object result = visitor.visitAstProgram(node);
        Logger.v(result.toString());
    }

}