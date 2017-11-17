package com.lfkdsk.justel.eval;

import com.lfkdsk.justel.JustEL;
import com.lfkdsk.justel.ast.base.AstNodeTest;
import com.lfkdsk.justel.context.JustMapContext;
import com.lfkdsk.justel.utils.logger.Logger;
import org.junit.jupiter.api.Test;

/**
 * Created by liufengkai on 2017/9/4.
 */
class ExpressionTest {

    @Test
    void eval() {
        Logger.init();
        Logger.i(JustEL
                .builder()
                .parser(lexer -> AstNodeTest.toNode("lfkdsk"))
                .compiler(code -> context -> context.get("lfkdsk"))
                .create()
                .eval("lfkdsk", new JustMapContext() {{
                    put("lfkdsk", 1000);
                }})
                .toString());
    }
}