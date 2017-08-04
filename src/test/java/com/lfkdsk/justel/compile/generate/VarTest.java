/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.compile.generate;

import com.lfkdsk.justel.utils.logger.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by liufengkai on 2017/8/4.
 */
class VarTest {


    @Test
    void testVar() {
        float lfkdsk = 10.1f;
        Var var = new Var("lfkdsk", lfkdsk);

        Logger.init("var test");
        Logger.i(var.generateVarAssignCode());

        Assertions.assertEquals(var.generateVarAssignCode(), "float lfkdsk=((java.lang.Float)context.get(\"lfkdsk\"));");
    }
}