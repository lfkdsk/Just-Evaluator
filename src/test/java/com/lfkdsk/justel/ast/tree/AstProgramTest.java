package com.lfkdsk.justel.ast.tree;

import org.junit.jupiter.api.Test;

import static com.lfkdsk.justel.compile.generate.JavaCodeGeneratorTest.compiler;

/**
 * Created by liufengkai on 2017/8/18.
 */
class AstProgramTest {

    @Test
    void testConst() {
        compiler("1111 + 111", null);
    }
}