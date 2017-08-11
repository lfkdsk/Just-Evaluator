package com.lfkdsk.justel.compile.compiler;

import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.context.JustMapContext;
import org.junit.jupiter.api.Test;

import static com.lfkdsk.justel.compile.generate.JavaCodeGeneratorTest.compiler;

/**
 * Created by liufengkai on 2017/8/11.
 */
public class CornerTest {

    @Test
    void ELEngineCornerTest1() {
        compiler("1000+100.0*99-(600-3*15)%(((68-9)-3)*2-100)+10000%7*71", null);
    }


    @Test
    void ELEngineCornerTest2() {
        JustContext vars = new JustMapContext();
        vars.put("i", 100);
        vars.put("pi", 3.14d);
        vars.put("d", -3.9);
        vars.put("b", (byte) 4);
        vars.put("bool", false);
        compiler("i * pi + (d * b - 199) / (1 - d * pi) - (2 + 100 - i / pi) % 99 ==i * pi + (d * b - 199) / (1 - d * pi) - (2 + 100 - i / pi) % 99", vars);
    }
}
