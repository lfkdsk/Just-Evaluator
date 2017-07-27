/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.parser;

import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.exception.ParseException;
import com.lfkdsk.justel.lexer.JustLexerImpl;
import com.lfkdsk.justel.lexer.Lexer;
import com.lfkdsk.justel.utils.logger.Logger;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static com.lfkdsk.justel.token.Token.EOF;

/**
 * Created by liufengkai on 2017/7/26.
 */
class JustParserImplTest {
    @Test
    void parser() throws ParseException {
        String lfkdsk = "lfkdsk(1111,2222,\"LFKDSK\")";
        Lexer lexer = new JustLexerImpl(new StringReader(lfkdsk));
        JustParser parser = new JustParserImpl();
        Logger.init("test parser");
        long start = System.currentTimeMillis();
        while (lexer.peek(0) != EOF) {
            AstNode node = parser.parser(lexer);
            Logger.v(" => " + node.toString() + "  ");
        }
        System.out.println(System.currentTimeMillis() - start);
    }
}