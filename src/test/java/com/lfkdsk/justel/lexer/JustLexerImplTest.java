package com.lfkdsk.justel.lexer;

import com.lfkdsk.justel.utils.logger.Logger;
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
        JustLexerImpl lexer = new JustLexerImpl(new StringReader(lfkdsk));
        Logger.init("logger test");
        Logger.d(lexer.read().toString());
        Logger.d(lexer.read().toString());
        Logger.d(lexer.read().toString());
        Logger.d(lexer.read().toString());
        Logger.d(lexer.read().toString());
        Logger.d(lexer.read().toString());
        Logger.d(lexer.read().toString());
    }
}