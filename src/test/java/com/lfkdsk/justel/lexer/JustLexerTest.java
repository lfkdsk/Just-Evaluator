package com.lfkdsk.justel.lexer;

import com.lfkdsk.justel.utils.logger.Logger;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by liufengkai on 2017/7/24.
 */
class JustLexerTest {

    @Test
    void testLexer() {
//        String lfkdsk = " 12 13.2222 \"lfkdsk\" lfkdsk 1200000000000 || &&";
        String lfkdsk = "|| &&";
        JustLexer lexer = new JustLexer(new StringReader(lfkdsk));
        Logger.init("logger test");
        Logger.d(lexer.read().toString());
        Logger.d(lexer.read().toString());
        Logger.d(lexer.read().toString());
        Logger.d(lexer.read().toString());
        Logger.d(lexer.read().toString());
    }
}