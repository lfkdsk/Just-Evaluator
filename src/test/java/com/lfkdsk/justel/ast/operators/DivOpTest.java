/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.ast.operators;

import org.junit.jupiter.api.Test;

import static com.lfkdsk.justel.parser.JustParserImplTest.runExpr;

/**
 * Created by liufengkai on 2017/7/29.
 */
class DivOpTest {

    @Test
    void testDivInteger() {
        runExpr("1 / 0", true, null);
    }

    @Test
    void testDivIntInt() {
        runExpr("1 / 1", true, null);
    }

    @Test
    void testDivIntFloat() {
        runExpr("2 / 0.5f ", true, null);
    }

    @Test
    void testDivIntDouble() {
        runExpr("1 / 0.111111d", true, null);
    }
}