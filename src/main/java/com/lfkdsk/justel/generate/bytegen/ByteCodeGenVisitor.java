package com.lfkdsk.justel.generate.bytegen;

import com.lfkdsk.justel.ast.base.AstLeaf;
import com.lfkdsk.justel.ast.base.AstList;
import com.lfkdsk.justel.ast.function.ExtendFunctionExpr;
import com.lfkdsk.justel.ast.function.OperatorExpr;
import com.lfkdsk.justel.ast.operators.*;
import com.lfkdsk.justel.ast.postfix.NegativePostfix;
import com.lfkdsk.justel.ast.postfix.NotPostfix;
import com.lfkdsk.justel.ast.tree.*;
import com.lfkdsk.justel.visitor.AstVisitor;

public class ByteCodeGenVisitor implements AstVisitor<Object> {

    @Override
    public Object visitAstLeaf(AstLeaf leaf) {
        return null;
    }

    @Override
    public Object visitAstList(AstList list) {
        return null;
    }

    @Override
    public Object visitExtendFunctionExpr(ExtendFunctionExpr extendFunctionExpr) {
        return null;
    }

    @Override
    public Object visitOperatorExpr(OperatorExpr operatorExpr) {
        return null;
    }

    @Override
    public Object visitAmpersandOp(AmpersandOp visitor) {
        return null;
    }

    @Override
    public Object visitAndOp(AndOp visitor) {
        return null;
    }

    @Override
    public Object visitArrayIndexExpr(ArrayIndexExpr visitor) {
        return null;
    }

    @Override
    public Object visitCondOp(CondOp visitor) {
        return null;
    }

    @Override
    public Object visitDivOp(DivOp visitor) {
        return null;
    }

    @Override
    public Object visitDotExpr(DotExpr visitor) {
        return null;
    }

    @Override
    public Object visitEqualOp(EqualOp visitor) {
        return null;
    }

    @Override
    public Object visitGreaterThanEqualOp(GreaterThanEqualOp visitor) {
        return null;
    }

    @Override
    public Object visitGreaterThanOp(GreaterThanOp visitor) {
        return null;
    }

    @Override
    public Object visitLessThanEqualOp(LessThanEqualOp visitor) {
        return null;
    }

    @Override
    public Object visitLessThanOp(LessThanOp visitor) {
        return null;
    }

    @Override
    public Object visitMinusOp(MinusOp visitor) {
        return null;
    }

    @Override
    public Object visitModOp(ModOp visitor) {
        return null;
    }

    @Override
    public Object visitMulOp(MulOp visitor) {
        return null;
    }

    @Override
    public Object visitOrOp(OrOp visitor) {
        return null;
    }

    @Override
    public Object visitPlusOp(PlusOp visitor) {
        return null;
    }

    @Override
    public Object visitUnEqualOp(UnEqualOp visitor) {
        return null;
    }

    @Override
    public Object visitNegativePostfix(NegativePostfix visitor) {
        return null;
    }

    @Override
    public Object visitNotPostfix(NotPostfix visitor) {
        return null;
    }

    @Override
    public Object visitAstBinaryExpr(AstBinaryExpr visitor) {
        return null;
    }

    @Override
    public Object visitAstCondExpr(AstCondExpr visitor) {
        return null;
    }

    @Override
    public Object visitAstFuncArguments(AstFuncArguments visitor) {
        return null;
    }

    @Override
    public Object visitAstFuncExpr(AstFuncExpr visitor) {
        return null;
    }

    @Override
    public Object visitAstPrimaryExpr(AstPrimaryExpr visitor) {
        return null;
    }

    @Override
    public Object visitAstProgram(AstProgram visitor) {
        return visitor.program()
                      .accept(this);
    }
}
