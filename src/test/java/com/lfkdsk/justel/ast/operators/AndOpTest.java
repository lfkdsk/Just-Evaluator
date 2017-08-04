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
 * Created by liufengkai on 2017/7/27.
 */
class AndOpTest {
    @Test
    void eval() {
        runExpr("true && true", true, new JustMapContext());
    }

    @Test
    void idAndEval() {
        JustContext context = new JustMapContext();
        context.put("lfkdsk", Boolean.TRUE);
        runExpr("lfkdsk && lfkdsk", true, context);
    }

    @Test
    void testAndCompiler() {
        compiler("true && true", new JustMapContext());
    }
}