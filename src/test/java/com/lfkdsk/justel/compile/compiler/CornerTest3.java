package com.lfkdsk.justel.compile.compiler;

import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.context.JustMapContext;
import org.junit.jupiter.api.Test;

import static com.lfkdsk.justel.compile.compiler.CompareTest.compiler;


/**
 * Created by liufengkai on 2017/8/11.
 */
public class CornerTest3 {
    @Test
    void ELEngineCornerTest() {
        JustContext vars = new JustMapContext();
        vars.put("i", 100);
        vars.put("pi", 3.14d);
        vars.put("d", -3.9);
        vars.put("b", (byte) 4);
        compiler(" pi*d+b-(1000-d*b/pi)/(pi+99-i*d)-i*pi*d/b", vars);
    }
}
