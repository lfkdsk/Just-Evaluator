/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.ast.postfix;

import org.junit.jupiter.api.Test;

import static com.lfkdsk.justel.parser.JustParserImplTest.runExpr;

/**
 * Created by liufengkai on 2017/7/28.
 */
class NegativeExprTest {

    @Test
    void testInteger() {
        runExpr("-11111", true, null);
    }

    @Test
    void testFloat() {
        runExpr("-1111.000111f", true, null);
    }

    @Test
    void testDouble() {
        runExpr("-1111.00110111d", true, null);
    }
}