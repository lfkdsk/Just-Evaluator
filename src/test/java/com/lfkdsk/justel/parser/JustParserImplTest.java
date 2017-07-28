/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.parser;

import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.context.JustContext;
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
        String lfkdsk0 = "lfkdsk() == false && lfkdsk";
        String lfkdsk1 = "(lfkdsk() ? lfkdsk : lfkdsk) ? true : false";
        runExpr(lfkdsk, false, null);
        runExpr(lfkdsk0, false, null);
        runExpr(lfkdsk1, false, null);
    }

    @Test
    void testThreeExpr() {
        runExpr("lfkdsk ? 1111 : ddddd", false, null);
    }

    @Test
    void testParser() {
        for (int i = 0; i < 20; i++) {
            long start = System.currentTimeMillis();
            for (int j = 0; j < 100; j++) {
                runExpr("lfkdsk.lfkdsk(1111,2222,\"LFKDSK\") == true", false, null);
            }
            System.out.println(System.currentTimeMillis() - start);
        }
    }

    public static void runExpr(String expr, boolean eval, JustContext context) {
        Lexer lexer = new JustLexerImpl(new StringReader(expr));
        JustParser parser = new JustParserImpl();
        Logger.init("test parser");

        while (lexer.hasMore()) {
            AstNode node = parser.parser(lexer);

//            Logger.v(" => " + node.toString() + "  ");
            if (eval) {
                Logger.v(" => " + node.eval(context) + "  ");
            }
        }

    }
}