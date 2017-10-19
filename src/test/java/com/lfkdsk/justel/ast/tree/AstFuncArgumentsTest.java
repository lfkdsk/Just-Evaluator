/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.ast.tree;

import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.context.JustMapContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.lfkdsk.justel.compile.generate.JavaCodeGeneratorTest.compiler;
import static com.lfkdsk.justel.parser.JustParserImplTest.runExpr;

/**
 * Created by liufengkai on 2017/8/2.
 */
public class AstFuncArgumentsTest {
    @Test
    void testParserFuncArgs() {
        // parser
        Assertions.assertEquals(
                runExpr("lfkdsk(\"lfkdsk\",lfkdsk,lfkdsk)", false, null)
                ,"(call lfkdsk \"lfkdsk\" lfkdsk lfkdsk)");
    }

    public static class O {
        private int lfkdsk = 100;

        private int[] lllll = new int[]{111, 111, 11222};

        public int[] getLllll() {
            return lllll;
        }

        public int getLfkdsk() {
            return lfkdsk;
        }
    }


    @Test
    void testGetter() {
        JustContext context = new JustMapContext();
        context.put("O", new O());
        runExpr("O.lfkdsk", true, context);
    }

    @Test
    void testCompilerGetter() {
        JustContext context = new JustMapContext();
        context.put("O", new O());
        compiler("O.lfkdsk", context);
        compiler("O.lllll[2]", context);
        compiler("O.getLllll()[2]", context);
    }
}