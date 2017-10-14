package com.lfkdsk.justel.repl;

import com.lfkdsk.justel.JustEL;
import com.lfkdsk.justel.context.JustMapContext;
import org.junit.jupiter.api.Test;

class MockAssignOperatorTest {

    @Test
    void eval() {
        JustEL.builder()
              .parser(new MockParser())
              .lexer(new MockLexer())
              .create()
              .eval("lfkdsk = \"lfkdsk\"", new JustMapContext());
    }

}