package com.lfkdsk.justel.lexer;

import org.junit.jupiter.api.Test;

import java.io.StringReader;

/**
 * Created by liufengkai on 2017/7/24.
 */
class JustLexerImplTest {

    @Test
    void testLexer() {
        String lfkdsk = " 12 13.2222 \"lfkdsk\" lfkdsk 1200000000000 || &&";
//        String lfkdsk = "|| &&";
        long startTime = System.currentTimeMillis();
        JustLexerImpl lexer = new JustLexerImpl(new StringReader(lfkdsk));

        lexer.read();
        lexer.read();
        lexer.read();
        lexer.read();
        lexer.read();
        lexer.read();
//        Logger.init("logger test");
//        Logger.d(lexer.read().toString());
//        Logger.d(lexer.read().toString());
//        Logger.d(lexer.read().toString());
//        Logger.d(lexer.read().toString());
//        Logger.d(lexer.read().toString());
//        Logger.d(lexer.read().toString());
//        Logger.d(lexer.read().toString());
        System.out.println(System.currentTimeMillis() - startTime);
    }
}