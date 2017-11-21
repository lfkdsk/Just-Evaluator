/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.compile.generate;

import com.lfkdsk.justel.ast.function.ExtendFunctionExpr;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.utils.logger.Logger;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

/**
 * Created by liufengkai on 2017/8/4.
 */
public class VarTest {

    private JustContext context = new JustContext() {

        @Override
        public boolean contain(String name) {
            return false;
        }

        @Override
        public Object remove(String key) {
            return null;
        }

        @Override
        public Object get(String objName) {
            return null;
        }

        @Override
        public Object put(String key, Object val) {
            return null;
        }

        @Override
        public Object getCache(Integer astHash) {
            return null;
        }

        @Override
        public Object putCache(Integer key, Object val) {
            return null;
        }

        @Override
        public ExtendFunctionExpr putExtendFunc(ExtendFunctionExpr expr) {
            return null;
        }

        @Override
        public ExtendFunctionExpr getExtendFunc(String name) {
            return null;
        }

        @Override
        public Object command(String command) {
            return null;
        }

        @Override
        public Collection<String> varsKeySet() {
            return null;
        }

        @Override
        public List<String> commandList() {
            return null;
        }

        @Override
        public List<String> functions() {
            return null;
        }

        @Override
        public boolean clearVars() {
            return false;
        }
    };

    @Test
    void testVar() {
        float lfkdsk = 10.1f;
        Var var = new Var("lfkdsk", lfkdsk);

        Logger.init("var test");
        Logger.i(context.generateVarAssignCode(var));

        Assertions.assertEquals(context.generateVarAssignCode(var), "float lfkdsk=((java.lang.Float)context.get(\"lfkdsk\"));");
    }

    @Test
    void testBoolVar() {
        Var var = new Var("lfkdsk", true);
        Logger.init("var bool test");
        Logger.i(context.generateVarAssignCode(var));
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
        Logger.i(context.generateVarAssignCode(var));
//        JustEL.runCompile("1000", new JustMapContext() {{
//            put("lfkdsk", integerComparable);
//        }});
    }

    @Test
    void testLambda() {
        Var var = new Var("lfkdsk", lambaInteger);
        Logger.init("var anonymous test");
        Logger.i(context.generateVarAssignCode(var));
    }
}