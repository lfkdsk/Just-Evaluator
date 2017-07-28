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
class MinusOpTest {
    @Test
    void testMinusInteger() {
        runExpr("111 - 100", true, null);
    }

    @Test
    void testMinusFloatInteger() {
        runExpr("111.1 - 10", true, null);
    }

    @Test
    void testMinusIntegerFloat() {
        runExpr("111 - 100.111111f", true, null);
    }

    @Test
    void testMinusDouble() {
        runExpr("111.00000001111d - 100", true, null);
    }
}