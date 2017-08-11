package com.lfkdsk.justel.ast.postfix;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.lfkdsk.justel.compile.generate.JavaCodeGeneratorTest.compiler;
import static com.lfkdsk.justel.parser.JustParserImplTest.runExpr;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by liufengkai on 2017/8/10.
 */
class NotPostfixTest {

    @Test
    void notEval() {
        String result = runExpr("!true", true, null);
        Assertions.assertFalse(Boolean.parseBoolean(result));
    }

    @Test
    void notCompiler() {
        String result = compiler("!true", null);
        Assertions.assertFalse(Boolean.parseBoolean(result));
    }
}