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
 * Created by liufengkai on 2017/7/28.
 */
class EqualOpTest {

    @Test
    void testUnEqualIntegerValue() {
        runExpr("1222 == 1222", true, null);
    }

    @Test
    void testUnEqualBoolean() {
        runExpr("true == true", true, null);
    }

    @Test
    void testUnEqualFloat() {
        runExpr("111.222f == 111.222f", true, null);
    }

    @Test
    void testUnEqualDouble() {
        runExpr("11111.22222222d == 11111.22222222d", true, null);
    }

}