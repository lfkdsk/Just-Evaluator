package com.lfkdsk.justel.ast.operators;

import com.lfkdsk.justel.context.JustMapContext;
import org.junit.jupiter.api.Test;

import static com.lfkdsk.justel.parser.JustParserImplTest.runExpr;

class AstSystemMethodExprTest {

    @Test
    void testSystemMethodExpr() {
        runExpr("\"lfkdsk\".isEmpty()", true, new JustMapContext());
    }
}