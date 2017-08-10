/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.literal;

import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.context.JustMapContext;
import com.lfkdsk.justel.utils.logger.Logger;
import com.lfkdsk.justel.utils.printer.Log;
import org.junit.jupiter.api.Test;

import static com.lfkdsk.justel.compile.generate.JavaCodeGeneratorTest.compiler;
import static com.lfkdsk.justel.parser.JustParserImplTest.runExpr;

/**
 * Created by liufengkai on 2017/8/3.
 */
class IDLiteralTest {

    @Test
    void testLocalVar() {
        JustContext context = new JustMapContext();
        context.put("id","SSSSSS");
        runExpr("id", false, context);
    }

    @Test
    void testLocalVarCompiler() {
        JustContext context = new JustMapContext();
        context.put("id","SSSSSS");
        Logger.init();
        Logger.i(compiler("id", context));
    }
}