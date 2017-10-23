package com.lfkdsk.justel.generate.bytecodegen;

import com.lfkdsk.justel.ast.base.AstLeaf;
import com.lfkdsk.justel.ast.base.AstList;
import com.lfkdsk.justel.ast.function.ExtendFunctionExpr;
import com.lfkdsk.justel.ast.function.OperatorExpr;
import com.lfkdsk.justel.ast.operators.*;
import com.lfkdsk.justel.ast.postfix.NegativePostfix;
import com.lfkdsk.justel.ast.postfix.NotPostfix;
import com.lfkdsk.justel.ast.tree.*;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.generate.WrapperGenCodeVisitor;
import com.lfkdsk.justel.literal.BoolLiteral;
import com.lfkdsk.justel.literal.IDLiteral;
import com.lfkdsk.justel.literal.NumberLiteral;
import com.lfkdsk.justel.literal.StringLiteral;

public class ByteCodeGenVisitor extends WrapperGenCodeVisitor{

    public ByteCodeGenVisitor(JustContext env) {
        super(env);
    }

    @Override
    public String visitAstLeaf(AstLeaf leaf) {
        return super.visitAstLeaf(leaf);
    }

    @Override
    public String visitAstList(AstList list) {
        return super.visitAstList(list);
    }

    @Override
    public String visitBoolLiteral(BoolLiteral visitor) {
        return super.visitBoolLiteral(visitor);
    }

    @Override
    public String visitIDLiteral(IDLiteral visitor) {
        return super.visitIDLiteral(visitor);
    }

    @Override
    public String visitNumberLiteral(NumberLiteral visitor) {
        return super.visitNumberLiteral(visitor);
    }

    @Override
    public String visitStringLiteral(StringLiteral visitor) {
        return super.visitStringLiteral(visitor);
    }

    @Override
    public String visitExtendFunctionExpr(ExtendFunctionExpr extendFunctionExpr) {
        return super.visitExtendFunctionExpr(extendFunctionExpr);
    }

    @Override
    public String visitOperatorExpr(OperatorExpr operatorExpr) {
        return super.visitOperatorExpr(operatorExpr);
    }

    @Override
    public String visitAmpersandOp(AmpersandOp visitor) {
        return super.visitAmpersandOp(visitor);
    }

    @Override
    public String visitAndOp(AndOp visitor) {
        return super.visitAndOp(visitor);
    }

    @Override
    public String visitArrayIndexExpr(ArrayIndexExpr visitor) {
        return super.visitArrayIndexExpr(visitor);
    }

    @Override
    public String visitCondOp(CondOp visitor) {
        return super.visitCondOp(visitor);
    }

    @Override
    public String visitDivOp(DivOp visitor) {
        return super.visitDivOp(visitor);
    }

    @Override
    public String visitDotExpr(DotExpr visitor) {
        return super.visitDotExpr(visitor);
    }

    @Override
    public String visitEqualOp(EqualOp visitor) {
        return super.visitEqualOp(visitor);
    }

    @Override
    public String visitGreaterThanEqualOp(GreaterThanEqualOp visitor) {
        return super.visitGreaterThanEqualOp(visitor);
    }

    @Override
    public String visitGreaterThanOp(GreaterThanOp visitor) {
        return super.visitGreaterThanOp(visitor);
    }

    @Override
    public String visitLessThanEqualOp(LessThanEqualOp visitor) {
        return super.visitLessThanEqualOp(visitor);
    }

    @Override
    public String visitLessThanOp(LessThanOp visitor) {
        return super.visitLessThanOp(visitor);
    }

    @Override
    public String visitMinusOp(MinusOp visitor) {
        return super.visitMinusOp(visitor);
    }

    @Override
    public String visitModOp(ModOp visitor) {
        return super.visitModOp(visitor);
    }

    @Override
    public String visitMulOp(MulOp visitor) {
        return super.visitMulOp(visitor);
    }

    @Override
    public String visitOrOp(OrOp visitor) {
        return super.visitOrOp(visitor);
    }

    @Override
    public String visitPlusOp(PlusOp visitor) {
        return super.visitPlusOp(visitor);
    }

    @Override
    public String visitUnEqualOp(UnEqualOp visitor) {
        return super.visitUnEqualOp(visitor);
    }

    @Override
    public String visitNegativePostfix(NegativePostfix visitor) {
        return super.visitNegativePostfix(visitor);
    }

    @Override
    public String visitNotPostfix(NotPostfix visitor) {
        return super.visitNotPostfix(visitor);
    }

    @Override
    public String visitAstBinaryExpr(AstBinaryExpr visitor) {
        return super.visitAstBinaryExpr(visitor);
    }

    @Override
    public String visitAstCondExpr(AstCondExpr visitor) {
        return super.visitAstCondExpr(visitor);
    }

    @Override
    public String visitAstFuncArguments(AstFuncArguments visitor) {
        return super.visitAstFuncArguments(visitor);
    }

    @Override
    public String visitAstFuncExpr(AstFuncExpr visitor) {
        return super.visitAstFuncExpr(visitor);
    }

    @Override
    public String visitAstPrimaryExpr(AstPrimaryExpr visitor) {
        return super.visitAstPrimaryExpr(visitor);
    }

    @Override
    public String visitAstProgram(AstProgram visitor) {
        return super.visitAstProgram(visitor);
    }
}
