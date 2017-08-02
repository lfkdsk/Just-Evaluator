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
 * Created by liufengkai on 2017/8/2.
 */
class LessThanOpTest {
    @Test
    void testLessThan() {
        String result = runExpr("123 < 123 ", true, null);
        Assertions.assertFalse(Boolean.valueOf(result));
    }

    @Test
    void testDoubleLessThan() {
        String result = runExpr("123.111111d < 111.111111d", true, null);
        Assertions.assertFalse(Boolean.valueOf(result));
    }

    @Test
    void testComparableLesshan() {
        String result = runExpr(" \"lfkdsk\" < \"lfk\" ", true, null);
        Assertions.assertFalse(Boolean.valueOf(result));
    }
}