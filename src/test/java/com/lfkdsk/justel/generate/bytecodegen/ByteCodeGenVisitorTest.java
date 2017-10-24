package com.lfkdsk.justel.generate.bytecodegen;

import com.lfkdsk.justel.context.JustMapContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ByteCodeGenVisitorTest {

    ByteCodeGenVisitor visitor;

    JustMapContext context;

    @BeforeEach
    void setUp() {
        context = new JustMapContext();
        visitor = new ByteCodeGenVisitor(context);
    }

    @Test
    void visitAstLeaf() {

    }

    @Test
    void visitAstList() {
    }

    @Test
    void visitBoolLiteral() {

    }

    @Test
    void visitIDLiteral() {
    }

    @Test
    void visitNumberLiteral() {
    }

    @Test
    void visitStringLiteral() {
    }

    @Test
    void visitExtendFunctionExpr() {
    }

    @Test
    void visitOperatorExpr() {
    }

    @Test
    void visitAmpersandOp() {
    }

    @Test
    void visitAndOp() {
    }

    @Test
    void visitArrayIndexExpr() {
    }

    @Test
    void visitCondOp() {
    }

    @Test
    void visitDivOp() {
    }

    @Test
    void visitDotExpr() {
    }

    @Test
    void visitEqualOp() {
    }

    @Test
    void visitGreaterThanEqualOp() {
    }

    @Test
    void visitGreaterThanOp() {
    }

    @Test
    void visitLessThanEqualOp() {
    }

    @Test
    void visitLessThanOp() {
    }

    @Test
    void visitMinusOp() {
    }

    @Test
    void visitModOp() {
    }

    @Test
    void visitMulOp() {
    }

    @Test
    void visitOrOp() {
    }

    @Test
    void visitPlusOp() {
    }

    @Test
    void visitUnEqualOp() {
    }

    @Test
    void visitNegativePostfix() {
    }

    @Test
    void visitNotPostfix() {
    }

    @Test
    void visitAstBinaryExpr() {
    }

    @Test
    void visitAstCondExpr() {
    }

    @Test
    void visitAstFuncArguments() {
    }

    @Test
    void visitAstFuncExpr() {
    }

    @Test
    void visitAstPrimaryExpr() {
    }

    @Test
    void visitAstProgram() {
    }

}