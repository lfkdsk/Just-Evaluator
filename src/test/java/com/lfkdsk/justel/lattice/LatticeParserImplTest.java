package com.lfkdsk.justel.lattice;

import com.lfkdsk.justel.lexer.JustLexerImpl;
import com.lfkdsk.justel.lexer.Lexer;
import com.lfkdsk.justel.utils.logger.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LatticeParserImplTest {

    LatticeParserImpl latticeParser;

    Lexer lexer;

    @BeforeEach
    void setUp() {
        latticeParser = new LatticeParserImpl();
        lexer = new JustLexerImpl();
        Logger.init();
    }

    @Test
    void testFactor() {
        lexer.reset("value");
        lexer.hasMore();
        latticeParser.parser(lexer).printAst();

        lexer.reset("((value))");
        lexer.hasMore();
        latticeParser.parser(lexer).printAst();
    }

    @Test
    void testParser() {
        lexer.reset("!\"seller\" isBSeller() and (\"category\" == \"50026316\" or \"category.rootCategoryId\" == \"50008141\" or \"category.rootCategoryId\" == \"50016422\" or \"category.rootCategoryId\" == \"50002766\" or \"category.rootCategoryId\" == \"50050359\" or \"category.rootCategoryId\" == \"50020275\" or \"category.rootCategoryId\" == \"124458005\")");
        lexer.hasMore();
        latticeParser.parser(lexer).printAst();
    }

    @Test
    void testEqual() {
        lexer.reset("\"category\" == \"50026316\"");
        lexer.hasMore();
        latticeParser.parser(lexer).printAst();
    }

    @Test
    void testNegative() {
        lexer.reset("!\"seller\" isBSeller()");
        lexer.hasMore();
        latticeParser.parser(lexer).printAst();
    }

    @Test
    void testDot() {
        lexer.reset("\"seller.asas\" isNotBlank");
        lexer.hasMore();
        latticeParser.parser(lexer).printAst();
    }
}