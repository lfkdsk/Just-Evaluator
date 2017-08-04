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
class ArrayIndexExprTest {

    @Test
    void testParser() {
        runExpr("lfkdsk[1]", false, null);
    }

    @Test
    void testArrayIndexExpr() {
        JustContext context = new JustMapContext();
//        Integer[][] lfkdsk = new Integer[][]{{1, 2}, {2, 3}, {2, 10}};
        int[][] lfkdsk = new int[][]{{1, 2}, {2, 3}, {2, 10}};
        context.put("lfkdsk", lfkdsk);
        String returnStr = runExpr("lfkdsk[2][1]", true, context);
        Assertions.assertEquals((int) Integer.valueOf(returnStr), 10);
    }

    @Test
    void testObjectArrayIndexExpr() {
        JustContext context = new JustMapContext();
        Integer[][] lfkdsk = new Integer[][]{{1, 2}, {2, 3}, {2, 10}};
        context.put("lfkdsk", lfkdsk);
        String returnStr = runExpr("lfkdsk[2][1]", true, context);
        Assertions.assertEquals((int) Integer.valueOf(returnStr), 10);
    }

    @Test
    void testArrayCompiler() {
        JustContext context = new JustMapContext();
        context.put("lfkdsk", new Object[]{1, 2, 2});
        compiler("lfkdsk[2]", context);
    }
}