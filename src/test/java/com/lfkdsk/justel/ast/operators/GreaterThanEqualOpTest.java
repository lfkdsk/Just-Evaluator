/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.ast.operators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.lfkdsk.justel.parser.JustParserImplTest.runExpr;

/**
 * Created by liufengkai on 2017/7/31.
 */
class GreaterThanEqualOpTest {
    @Test
    void testGTE() {
        String returnStr = runExpr("11111 >= 11111", true, null);
        Assertions.assertTrue(Boolean.valueOf(returnStr));
    }

    @Test
    void testComparableGTE() {
        String returnStr = runExpr("\"lfkdsk\" >= \"lfk\" ", true, null);
        Assertions.assertTrue(Boolean.valueOf(returnStr));
    }

    @Test
    void testDoubleGTE() {
        String returnStr = runExpr("1111.000100d >= 1000.11111d", true, null);
        Assertions.assertTrue(Boolean.valueOf(returnStr));
    }
}