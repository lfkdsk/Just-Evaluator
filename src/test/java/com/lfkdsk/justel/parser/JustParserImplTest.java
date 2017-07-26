/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.parser;

import com.lfkdsk.justel.lexer.JustLexerImpl;
import com.lfkdsk.justel.lexer.Lexer;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

/**
 * Created by liufengkai on 2017/7/26.
 */
class JustParserImplTest {
    @Test
    void parser() {
        String lfkdsk = "";
        Lexer lexer = new JustLexerImpl(new StringReader(lfkdsk));
        JustParser parser = new JustParserImpl();
        parser.parser(lexer);
    }
}