/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.compile.generate;

import com.lfkdsk.justel.utils.logger.Logger;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by liufengkai on 2017/8/4.
 */
public class VarTest {


    @Test
    void testVar() {
        float lfkdsk = 10.1f;
        Var var = new Var("lfkdsk", lfkdsk);

        Logger.init("var test");
        Logger.i(var.generateVarAssignCode());

        Assertions.assertEquals(var.generateVarAssignCode(), "float lfkdsk=((java.lang.Float)context.get(\"lfkdsk\"));");
    }

    @Test
    void testBoolVar() {
        Var var = new Var("lfkdsk", true);
        Logger.init("var bool test");
        Logger.i(var.generateVarAssignCode());
    }

    public
    Comparable<Integer> integerComparable = new Comparable<Integer>() {
        @Override
        public int compareTo(@NotNull Integer o) {
            return 0;
        }
    };

    public Comparable<Integer> lambaInteger = o -> 0;

    @Test
    void testAnonymousClass() {
        Var var = new Var("lfkdsk", integerComparable);
        Logger.init("var anonymous test");
        Logger.i(var.generateVarAssignCode());
//        JustEL.runCompile("1000", new JustMapContext() {{
//            put("lfkdsk", integerComparable);
//        }});
    }

    @Test
    void testLambda() {
        Var var = new Var("lfkdsk", lambaInteger);
        Logger.init("var anonymous test");
        Logger.i(var.generateVarAssignCode());
    }
}