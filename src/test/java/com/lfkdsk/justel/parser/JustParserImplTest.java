/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.parser;

import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.context.JustMapContext;
import com.lfkdsk.justel.exception.ParseException;
import com.lfkdsk.justel.lexer.JustLexerImpl;
import com.lfkdsk.justel.lexer.Lexer;
import com.lfkdsk.justel.utils.logger.Logger;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

/**
 * Just Parser Impl Test
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/26.
 * @see JustParserImpl
 */
public class JustParserImplTest {
    @Test
    void parser() throws ParseException {
        String lfkdsk = "lfkdsk.LFKDSK[11111 + 12222](1111,2222,\"LFKDSK\") == true";
        runExpr(lfkdsk);
    }

    @Test
    void testThreeExpr() {
        runExpr("lfkdsk ? 1111 : ddddd");
    }

    public static void runExpr(String expr) {
        Lexer lexer = new JustLexerImpl(new StringReader(expr));
        JustParser parser = new JustParserImpl();
        Logger.init("test parser");
        long start = System.currentTimeMillis();
        while (lexer.hasMore()) {
            AstNode node = parser.parser(lexer);
            node.eval(new JustMapContext());
            Logger.v(" => " + node.toString() + "  ");
        }
        System.out.println(System.currentTimeMillis() - start);

    }
}