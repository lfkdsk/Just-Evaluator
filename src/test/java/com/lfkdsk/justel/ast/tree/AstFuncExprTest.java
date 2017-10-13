/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.ast.tree;

import org.junit.jupiter.api.Test;

import static com.lfkdsk.justel.parser.JustParserImplTest.runExpr;

/**
 * Created by liufengkai on 2017/8/2.
 */
class AstFuncExprTest {

    @Test
    void testAstFunc() {
        runExpr("lfkdsk.function(1111,2222)", false, null);
    }

    @Test
    void testPoint() {
        runExpr("a.c", false, null);
    }

    @Test
    void testAstFuncParser() {
        runExpr("function(1111,2222)", false, null);
    }
}