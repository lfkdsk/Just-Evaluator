package com.lfkdsk.justel.ast.postfix;

import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.context.JustMapContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.lfkdsk.justel.compile.generate.JavaCodeGeneratorTest.compiler;
import static com.lfkdsk.justel.parser.JustParserImplTest.runExpr;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by liufengkai on 2017/8/10.
 */
class NegativePostfixTest {

    @Test
    void negativeEval() {
        JustContext context = new JustMapContext();
        context.put("lfkdsk", 100);
        String result = runExpr("- lfkdsk ", true, context);
        Assertions.assertEquals(Integer.parseInt(result), -100);
    }

    @Test
    void negativeCompiler() {
        JustContext context = new JustMapContext();
        context.put("lfkdsk", 100);
        String result = compiler("- lfkdsk ", context);
        Assertions.assertEquals(Integer.parseInt(result), -100);
    }

}