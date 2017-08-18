package com.lfkdsk.justel.lexer;

import org.junit.jupiter.api.Test;

import java.io.StringReader;

/**
 * Created by liufengkai on 2017/7/24.
 */
class JustLexerImplTest {

    @Test
    void testLexer() {
        for (int j = 0; j < 20; j++) {
            String lfkdsk = String.valueOf(" \"" + RandomUtils.RandomString(10) + "\""
                    + " " + RandomUtils.RandomFloat(2, 20) + " " +
                    RandomUtils.RandomInt(10, 200) + " " + "|| && *");

            long startTime = System.currentTimeMillis();
            for (int i = 0; i < 1000; i++) {
                JustLexerImpl lexer = new JustLexerImpl(new StringReader(lfkdsk));
                lexer.hasMore();
            }
            System.out.println(System.currentTimeMillis() - startTime);
        }
    }

    @Test
    void testNewSymbol() {
        JustLexerImpl lexer = new JustLexerImpl(new StringReader("== !== #= != "));
        lexer.hasMore();
    }
}