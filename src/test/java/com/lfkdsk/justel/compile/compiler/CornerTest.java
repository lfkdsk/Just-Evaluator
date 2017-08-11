package com.lfkdsk.justel.compile.compiler;

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


}
