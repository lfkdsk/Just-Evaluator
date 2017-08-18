package com.lfkdsk.justel.ast.tree;

import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.context.JustMapContext;
import org.junit.jupiter.api.Test;

import static com.lfkdsk.justel.compile.generate.JavaCodeGeneratorTest.compiler;
import static com.lfkdsk.justel.parser.JustParserImplTest.runExpr;

/**
 * Created by liufengkai on 2017/8/10.
 */
class AstCondExprTest {

    @Test
    void condExpr() {
        runExpr("true ? 100 : -100", true, null);
    }

    @Test
    void condCompiler() {
        JustContext context = new JustMapContext();
        context.put("lfkdsk", 101);
        compiler("lfkdsk > 100 ? 100 : -100", context);
    }

}