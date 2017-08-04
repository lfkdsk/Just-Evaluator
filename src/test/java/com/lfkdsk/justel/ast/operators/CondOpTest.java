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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.lfkdsk.justel.compile.generate.JavaCodeGeneratorTest.compiler;
import static com.lfkdsk.justel.parser.JustParserImplTest.runExpr;

/**
 * Created by liufengkai on 2017/8/2.
 */
class CondOpTest {

    @Test
    void testCondOp() {
        String returnStr = runExpr("true ? true : false", true, null);
        Assertions.assertTrue(Boolean.valueOf(returnStr));
    }

    @Test
    void testExprCondOp() {
        JustContext context = new JustMapContext();
        context.put("lfkdsk", 101);
        String returnStr = runExpr("lfkdsk > 100 ? lfkdsk + 1 : lfkdsk + 2", true, context);
        Assertions.assertEquals((int) Integer.valueOf(returnStr), 102);
    }

    @Test
    void testExprCompileCond() {
        JustContext context = new JustMapContext();
        context.put("lfkdsk", true);
        compiler("lfkdsk ? 123 : 123.123f", context);
    }
}