package com.lfkdsk.justel.repl;

import com.lfkdsk.justel.JustEL;
import org.junit.jupiter.api.Test;

class MockAssignOperatorTest {
    @Test
    void compile() {
    }

    @Test
    void eval() {
        JustEL.builder()
              .parser(new MockParser())
              .create()
              .eval("lfkdsk = \"lfkdsk\"",null);
    }

}