/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.ast.function;

import com.lfkdsk.justel.ast.tree.AstFuncExpr;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.context.JustMapContext;
import com.lfkdsk.justel.utils.logger.Logger;
import com.lfkdsk.justel.utils.printer.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.lfkdsk.justel.compile.generate.JavaCodeGeneratorTest.compiler;
import static com.lfkdsk.justel.parser.JustParserImplTest.runExpr;

/**
 * Created by liufengkai on 2017/8/3.
 */
public class ExtendFunctionExprTest {

    public static class ExtendFunc extends ExtendFunctionExpr {

        @Override
        public String functionName() {
            return "add";
        }

        @Override
        public Object eval(Object... params) {
            Integer left = (Integer) params[0];
            Integer right = (Integer) params[1];

            if (left != null && right != null) {
                return left + right;
            }

            return this.eval(params);
        }
    }


    @Test
    void testExtendFunc() {
        ExtendFunc func = new ExtendFunc();
        AstFuncExpr.extFunc.put(func.functionName(), func);
        String returnStr = runExpr("add(1111,2222)", true, null);
        Assertions.assertEquals((int) Integer.valueOf(returnStr), 3333);
    }


    @Test
    void testExtendFuncCompiler() {
        Logger.init();
        ExtendFunc func = new ExtendFunc();
        AstFuncExpr.extFunc.put(func.functionName(), func);
        JustContext context = new JustMapContext();
        String returnStr = compiler("add(1111,2222)", context);
        Assertions.assertEquals((int) Integer.valueOf(returnStr), 3333);
    }
}