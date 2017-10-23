package com.lfkdsk.justel.generate;

import com.lfkdsk.justel.ast.base.AstLeaf;
import com.lfkdsk.justel.ast.base.AstList;
import com.lfkdsk.justel.ast.function.ExtendFunctionExpr;
import com.lfkdsk.justel.ast.function.OperatorExpr;
import com.lfkdsk.justel.ast.operators.*;
import com.lfkdsk.justel.ast.postfix.NegativePostfix;
import com.lfkdsk.justel.ast.postfix.NotPostfix;
import com.lfkdsk.justel.ast.tree.*;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.literal.BoolLiteral;
import com.lfkdsk.justel.literal.IDLiteral;
import com.lfkdsk.justel.literal.NumberLiteral;
import com.lfkdsk.justel.literal.StringLiteral;
import com.lfkdsk.justel.visitor.AstVisitor;

/**
 * Wrapper For Java Code Gen
 *
 * @author liufengkai
 */
public class WrapperGenCodeVisitor implements AstVisitor<String> {

    private JustContext env;

    public WrapperGenCodeVisitor(JustContext env) {
        this.env = env;
    }

    @Override
    public String visitAstLeaf(AstLeaf leaf) {
        return leaf.toString();
    }

    @Override
    public String visitAstList(AstList list) {
        return list.compile(env);
    }

    @Override
    public String visitBoolLiteral(BoolLiteral visitor) {
        return visitor.compile(env);
    }

    @Override
    public String visitIDLiteral(IDLiteral visitor) {
        return visitor.compile(env);
    }

    @Override
    public String visitNumberLiteral(NumberLiteral visitor) {
        return visitor.compile(env);
    }

    @Override
    public String visitStringLiteral(StringLiteral visitor) {
        return visitor.compile(env);
    }

    @Override
    public String visitExtendFunctionExpr(ExtendFunctionExpr extendFunctionExpr) {
        throw new UnsupportedOperationException("Unsupported this node's compile | please read AstFuncExpr.java ");
    }

    @Override
    public String visitOperatorExpr(OperatorExpr operatorExpr) {
        return operatorExpr.compile(env);
    }

    @Override
    public String visitAmpersandOp(AmpersandOp visitor) {
        return visitor.compile(env);
    }

    @Override
    public String visitAndOp(AndOp visitor) {
        return visitor.compile(env);
    }

    @Override
    public String visitArrayIndexExpr(ArrayIndexExpr visitor) {
        return visitor.compile(env);
    }

    @Override
    public String visitCondOp(CondOp visitor) {
        return visitor.compile(env);
    }

    @Override
    public String visitDivOp(DivOp visitor) {
        return visitor.compile(env);
    }

    @Override
    public String visitDotExpr(DotExpr visitor) {
        return visitor.compile(env);
    }

    @Override
    public String visitEqualOp(EqualOp visitor) {
        return visitor.compile(env);
    }

    @Override
    public String visitGreaterThanEqualOp(GreaterThanEqualOp visitor) {
        return visitor.compile(env);
    }

    @Override
    public String visitGreaterThanOp(GreaterThanOp visitor) {
        return visitor.compile(env);
    }

    @Override
    public String visitLessThanEqualOp(LessThanEqualOp visitor) {
        return visitor.compile(env);
    }

    @Override
    public String visitLessThanOp(LessThanOp visitor) {
        return visitor.compile(env);
    }

    @Override
    public String visitMinusOp(MinusOp visitor) {
        return visitor.compile(env);
    }

    @Override
    public String visitModOp(ModOp visitor) {
        return visitor.compile(env);
    }

    @Override
    public String visitMulOp(MulOp visitor) {
        return visitor.compile(env);
    }

    @Override
    public String visitOrOp(OrOp visitor) {
        return visitor.compile(env);
    }

    @Override
    public String visitPlusOp(PlusOp visitor) {
        return visitor.compile(env);
    }

    @Override
    public String visitUnEqualOp(UnEqualOp visitor) {
        return visitor.compile(env);
    }

    @Override
    public String visitNegativePostfix(NegativePostfix visitor) {
        return visitor.compile(env);
    }

    @Override
    public String visitNotPostfix(NotPostfix visitor) {
        return visitor.compile(env);
    }

    @Override
    public String visitAstBinaryExpr(AstBinaryExpr visitor) {
        return visitor.compile(env);
    }

    @Override
    public String visitAstCondExpr(AstCondExpr visitor) {
        return visitor.compile(env);
    }

    @Override
    public String visitAstFuncArguments(AstFuncArguments visitor) {
        return visitor.compile(env);
    }

    @Override
    public String visitAstFuncExpr(AstFuncExpr visitor) {
        return visitor.compile(env);
    }

    @Override
    public String visitAstPrimaryExpr(AstPrimaryExpr visitor) {
        return visitor.compile(env);
    }

    @Override
    public String visitAstProgram(AstProgram visitor) {
        return visitor.compile(env);
    }
}
