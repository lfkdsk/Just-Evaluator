package com.lfkdsk.justel.literal;

import com.lfkdsk.justel.utils.logger.Logger;
import org.junit.jupiter.api.Test;

import static com.lfkdsk.justel.compile.generate.JavaCodeGeneratorTest.compiler;
import static com.lfkdsk.justel.parser.JustParserImplTest.runExpr;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by liufengkai on 2017/8/10.
 */
class BoolLiteralTest {

    @Test
    void boolEval() {
        runExpr("true", true, null);
    }

    @Test
    void boolCompile() {
        Logger.init();
        Logger.i(compiler("true", null));
    }
}