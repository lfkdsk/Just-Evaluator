/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.ast.operators;

import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.context.JustMapContext;
import com.lfkdsk.justel.utils.logger.Logger;
import org.junit.jupiter.api.Test;

import static com.lfkdsk.justel.compile.generate.JavaCodeGeneratorTest.compiler;
import static com.lfkdsk.justel.parser.JustParserImplTest.runExpr;

/**
 * Created by liufengkai on 2017/7/28.
 */
class PlusOpTest {

    @Test
    void testPlusString() {
        JustContext context = new JustMapContext();
        context.put("lfkdsk", "1222");
        context.put("lfk", "1&&22");

        runExpr("lfkdsk + lfk", true, context);
    }

    @Test
    void testPlusIDInteger() {
        JustContext context = new JustMapContext();
        context.put("lfkdsk", 111);
        context.put("lfk", 222);

        runExpr("lfkdsk + lfk", true, context);
    }

    @Test
    void testPlusInteger() {
        runExpr("111 + 222", true, null);
    }

    @Test
    void testPlusIntegerFloat() {
        runExpr("111 + 222.222", true, null);
    }

    @Test
    void testPlusFloatFloat() {
        runExpr("111.11f + 222.222f", true, null);
    }

    @Test
    void testPlusDoubleDouble() {
        runExpr("111.111111d + 222.222d", true, null);
    }

    @Test
    void testPlusCompiler() {
        JustContext context = new JustMapContext();
        context.put("lfkdsk", "1222");
        context.put("lfk", "1&&22");
        compiler("lfkdsk + lfk", context);

        compiler("111 + 222", null);
        compiler("111 + 222.222", null);
        compiler("111.11f+222.222f", null);
        compiler("111.111111d + 222.222d", null);
    }

    @Test
    void testForConst() {
        Logger.init();
        compiler("1000+100.0*99-(600-3*15)%(((68-9)-3)*2-100)+10000%7*71", null);
    }
}