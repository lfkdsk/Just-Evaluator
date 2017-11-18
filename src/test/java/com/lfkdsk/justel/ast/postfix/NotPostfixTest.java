package com.lfkdsk.justel.ast.postfix;

import com.lfkdsk.justel.context.JustMapContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.lfkdsk.justel.compile.generate.JavaCodeGeneratorTest.compiler;
import static com.lfkdsk.justel.parser.JustParserImplTest.runExpr;

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

    @Test
    void notEqualCompiler() {
        String result = compiler("lfkdsk == 1000 && !(lfkdsk == 1000) && lfkdsk == 1000 && lfkdsk == 1000 && lfkdsk == 1000 && lfkdsk == 1000 && lfkdsk == 1000"
                , new JustMapContext(){{
            put("lfkdsk",1000);
        }});
        Assertions.assertFalse(Boolean.parseBoolean(result));
    }
}