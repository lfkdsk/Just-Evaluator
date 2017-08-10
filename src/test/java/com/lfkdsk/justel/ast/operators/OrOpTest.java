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
import org.junit.jupiter.api.Test;

import static com.lfkdsk.justel.compile.generate.JavaCodeGeneratorTest.compiler;
import static com.lfkdsk.justel.parser.JustParserImplTest.runExpr;

/**
 * Created by liufengkai on 2017/7/29.
 */
class OrOpTest {

    @Test
    void testOrBoolean() {
        runExpr("true || false", true, null);
    }

    @Test
    void testOrIDToken() {
        JustContext context = new JustMapContext();
        context.put("lfkdsk", true);
        context.put("lfk", true);
        runExpr("lfkdsk || lfk", true, context);
    }

    @Test
    void testOrCompiler() {
        compiler("true || false", null);

        JustContext context = new JustMapContext();
        context.put("lfkdsk", true);
        context.put("lfk", true);
        compiler("lfkdsk || lfk", context);
    }
}