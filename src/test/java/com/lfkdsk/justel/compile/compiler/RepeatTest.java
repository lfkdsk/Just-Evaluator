package com.lfkdsk.justel.compile.compiler;

import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.context.JustMapContext;
import org.junit.jupiter.api.Test;

import static com.lfkdsk.justel.compile.compiler.CornerTest.compiler;

/**
 * Created by liufengkai on 2017/9/28.
 */
public class RepeatTest {
    static String expr = "i == 100 || i == 100|| i == 100|| i == 100|| i == 100";

    @Test
    void testRepeat() {
        JustContext vars = new JustMapContext();
        vars.put("i", 100);
        compiler(expr, vars);
    }
}
