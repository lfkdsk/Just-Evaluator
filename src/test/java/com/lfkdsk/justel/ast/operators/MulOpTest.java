/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.ast.operators;

import org.junit.jupiter.api.Test;

import static com.lfkdsk.justel.compile.generate.JavaCodeGeneratorTest.compiler;
import static com.lfkdsk.justel.parser.JustParserImplTest.runExpr;

/**
 * Created by liufengkai on 2017/7/29.
 */
class MulOpTest {

    @Test
    void testMulInteger() {
        runExpr("2 * 2000", true, null);
    }

    @Test
    void testMulFloat() {
        runExpr("2.1111f * 2000.1111f", true, null);
    }

    @Test
    void testMulDouble() {
        runExpr("2.11111d * 2000.222222d", true, null);
    }

    @Test
    void testMulCompiler() {
        compiler("2 * 2000", null);

        compiler("2.1111f * 2000.1111f", null);

        compiler("2.11111d * 2000.222222d", null);
    }
}