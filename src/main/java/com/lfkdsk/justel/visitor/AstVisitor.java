package com.lfkdsk.justel.visitor;

import com.lfkdsk.justel.ast.base.AstLeaf;
import com.lfkdsk.justel.ast.base.AstList;
import com.lfkdsk.justel.ast.function.ExtendFunctionExpr;
import com.lfkdsk.justel.ast.function.OperatorExpr;
import com.lfkdsk.justel.ast.operators.*;
import com.lfkdsk.justel.ast.postfix.NegativePostfix;
import com.lfkdsk.justel.ast.postfix.NotPostfix;
import com.lfkdsk.justel.ast.tree.*;
import com.lfkdsk.justel.literal.BoolLiteral;
import com.lfkdsk.justel.literal.IDLiteral;
import com.lfkdsk.justel.literal.NumberLiteral;
import com.lfkdsk.justel.literal.StringLiteral;

public interface AstVisitor<T> {

    ///////////////////////////////////////////////////////////////////////////
    // base interface
    ///////////////////////////////////////////////////////////////////////////

    T visitAstLeaf(AstLeaf leaf);

    T visitAstList(AstList list);

    ///////////////////////////////////////////////////////////////////////////
    // literal interface
    ///////////////////////////////////////////////////////////////////////////

    T visitBoolLiteral(BoolLiteral visitor);

    T visitIDLiteral(IDLiteral visitor);

    T visitNumberLiteral(NumberLiteral visitor);

    T visitStringLiteral(StringLiteral visitor);

    ///////////////////////////////////////////////////////////////////////////
    // function interface
    ///////////////////////////////////////////////////////////////////////////

    T visitExtendFunctionExpr(ExtendFunctionExpr extendFunctionExpr);

    T visitOperatorExpr(OperatorExpr operatorExpr);

    ///////////////////////////////////////////////////////////////////////////
    // operators interface
    ///////////////////////////////////////////////////////////////////////////

    T visitAmpersandOp(AmpersandOp visitor);

    T visitAndOp(AndOp visitor);

    T visitArrayIndexExpr(ArrayIndexExpr visitor);

    T visitCondOp(CondOp visitor);

    T visitDivOp(DivOp visitor);

    T visitDotExpr(DotExpr visitor);

    T visitEqualOp(EqualOp visitor);

    T visitGreaterThanEqualOp(GreaterThanEqualOp visitor);

    T visitGreaterThanOp(GreaterThanOp visitor);

    T visitLessThanEqualOp(LessThanEqualOp visitor);

    T visitLessThanOp(LessThanOp visitor);

    T visitMinusOp(MinusOp visitor);

    T visitModOp(ModOp visitor);

    T visitMulOp(MulOp visitor);

    T visitOrOp(OrOp visitor);

    T visitPlusOp(PlusOp visitor);

    T visitUnEqualOp(UnEqualOp visitor);

    ///////////////////////////////////////////////////////////////////////////
    // postfix interface
    ///////////////////////////////////////////////////////////////////////////

    T visitNegativePostfix(NegativePostfix visitor);

    T visitNotPostfix(NotPostfix visitor);

    ///////////////////////////////////////////////////////////////////////////
    // tree interface
    ///////////////////////////////////////////////////////////////////////////

    T visitAstBinaryExpr(AstBinaryExpr visitor);

    T visitAstCondExpr(AstCondExpr visitor);

    T visitAstFuncArguments(AstFuncArguments visitor);

    T visitAstFuncExpr(AstFuncExpr visitor);

    T visitAstPrimaryExpr(AstPrimaryExpr visitor);

    T visitAstProgram(AstProgram visitor);
}
