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
class AmpersandOpTest {

    @Test
    void testAmpersandInteger() {
        runExpr("1111 & 1111", true, null);
    }

    @Test
    void testAmpersandID() {
        JustContext context = new JustMapContext();
        context.put("lfkdsk", 1111);
        runExpr("lfkdsk & 1211", true, context);

        Logger.i(String.valueOf(1111 & 1211));
    }

    @Test
    void testAmpersandCompiler() {
        JustContext context = new JustMapContext();
        context.put("lfkdsk", 1111);

        compiler("lfkdsk & 1211", context);
    }
}