package com.lfkdsk.justel.ast.tree;

import org.junit.jupiter.api.Test;

import static com.lfkdsk.justel.parser.JustParserImplTest.runExpr;


class AstPrimaryExprTest {

    @Test
    void testRecursiveFunction() {
        runExpr("lfkdsk[1][2]", false, null);
    }
}