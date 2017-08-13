/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.ast.operators;

import com.lfkdsk.justel.utils.NumberUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.lfkdsk.justel.compile.generate.JavaCodeGeneratorTest.compiler;
import static com.lfkdsk.justel.parser.JustParserImplTest.runExpr;

/**
 * Created by liufengkai on 2017/8/2.
 */
class ModTest {

    @Test
    void testModInteger() {
        String returnStr = runExpr("100 % 10", true, null);
        Assertions.assertEquals((int) Integer.valueOf(returnStr), 0);
    }

    @Test
    void testModDouble() {
        String returnStr = runExpr("1111.1111d % 10.1111d", true, null);
        Assertions.assertEquals((double) Double.valueOf(returnStr), 9.001199999999999);
    }

    @Test
    void testModCompiler() {
        String returnStr = compiler("100 % 10", null);
        Assertions.assertEquals((int) Integer.valueOf(returnStr), 0);

        returnStr = compiler("1111.1111d % 10.1111d", null);
        Assertions.assertEquals((double) Double.valueOf(returnStr), 9.001199999999999);
    }

    @Test
    void testModMethod() {
        NumberUtils.computeDivValue(0, 0);
    }
}