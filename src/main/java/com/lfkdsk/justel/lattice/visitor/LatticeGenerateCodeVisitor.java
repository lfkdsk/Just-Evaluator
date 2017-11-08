package com.lfkdsk.justel.lattice.visitor;

import com.lfkdsk.justel.ast.base.AstLeaf;
import com.lfkdsk.justel.ast.base.AstList;
import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.ast.function.ExtendFunctionExpr;
import com.lfkdsk.justel.ast.function.OperatorExpr;
import com.lfkdsk.justel.ast.operators.*;
import com.lfkdsk.justel.ast.postfix.NegativePostfix;
import com.lfkdsk.justel.ast.postfix.NotPostfix;
import com.lfkdsk.justel.ast.tree.*;
import com.lfkdsk.justel.lattice.ast.AstFunctionName;
import com.lfkdsk.justel.lattice.ast.AstSystemFunction;
import com.lfkdsk.justel.literal.BoolLiteral;
import com.lfkdsk.justel.literal.IDLiteral;
import com.lfkdsk.justel.literal.NumberLiteral;
import com.lfkdsk.justel.literal.StringLiteral;
import com.lfkdsk.justel.utils.visitor.AstVisitor;

public class LatticeGenerateCodeVisitor implements AstVisitor<String> {

    @Override
    public String visitAstLeaf(AstLeaf leaf) {
        return leaf.toString();
    }

    @Override
    public String visitAstList(AstList list) {
        StringBuilder builder = new StringBuilder();

        builder.append('(');

        String sep = "";

        for (AstNode node : list) {
            builder.append(sep);
            sep = " ";
            builder.append(node.accept(this));
        }

        return builder.append(')').toString();
    }

    @Override
    public String visitBoolLiteral(BoolLiteral visitor) {
        return String.valueOf(visitor.value());
    }

    @Override
    public String visitIDLiteral(IDLiteral visitor) {
        return visitor.name();
    }

    @Override
    public String visitNumberLiteral(NumberLiteral visitor) {
        return visitor.toString();
    }

    @Override
    public String visitStringLiteral(StringLiteral visitor) {
        for (String token : new String[]{"standard", "item", "seller", "buyer", "category", "source"}) {
            if (visitor.value().contains(token)) {
                return visitor.value();
            }
        }

        return "(\"" + visitor.value() + "\")";
    }

    @Override
    public String visitExtendFunctionExpr(ExtendFunctionExpr extendFunctionExpr) {
        return null;
    }

    @Override
    public String visitOperatorExpr(OperatorExpr operatorExpr) {
        return (operatorExpr.leftChild().accept(this) +
                " " + operatorExpr.operator().accept(this) +
                " " + operatorExpr.rightChild().accept(this));
    }

    @Override
    public String visitAmpersandOp(AmpersandOp visitor) {
        return null;
    }

    @Override
    public String visitAndOp(AndOp visitor) {
        return (visitor.leftChild().accept(this) +
                " && " +
                " " + visitor.rightChild().accept(this));
    }

    @Override
    public String visitArrayIndexExpr(ArrayIndexExpr visitor) {
        return null;
    }

    @Override
    public String visitCondOp(CondOp visitor) {
        return null;
    }

    @Override
    public String visitDivOp(DivOp visitor) {
        return null;
    }

    @Override
    public String visitDotExpr(DotExpr visitor) {
        return null;
    }

    @Override
    public String visitEqualOp(EqualOp visitor) {
        return visitOperatorExpr(visitor);
    }

    @Override
    public String visitGreaterThanEqualOp(GreaterThanEqualOp visitor) {
        return visitOperatorExpr(visitor);
    }

    @Override
    public String visitGreaterThanOp(GreaterThanOp visitor) {
        return visitOperatorExpr(visitor);

    }

    @Override
    public String visitLessThanEqualOp(LessThanEqualOp visitor) {
        return visitOperatorExpr(visitor);
    }

    @Override
    public String visitLessThanOp(LessThanOp visitor) {
        return visitOperatorExpr(visitor);
    }

    @Override
    public String visitMinusOp(MinusOp visitor) {
        return visitOperatorExpr(visitor);
    }

    @Override
    public String visitModOp(ModOp visitor) {
        return null;
    }

    @Override
    public String visitMulOp(MulOp visitor) {
        return null;
    }

    @Override
    public String visitOrOp(OrOp visitor) {
        return (visitor.leftChild().accept(this) +
                " || " +
                " " + visitor.rightChild().accept(this));
    }

    @Override
    public String visitPlusOp(PlusOp visitor) {
        return null;
    }

    @Override
    public String visitUnEqualOp(UnEqualOp visitor) {
        return visitOperatorExpr(visitor);
    }

    @Override
    public String visitNegativePostfix(NegativePostfix visitor) {
        return visitor.toString();
    }

    @Override
    public String visitNotPostfix(NotPostfix visitor) {
        return visitor.toString();
    }

    @Override
    public String visitAstBinaryExpr(AstBinaryExpr visitor) {
        return null;
    }

    @Override
    public String visitAstCondExpr(AstCondExpr visitor) {
        return null;
    }

    @Override
    public String visitAstFuncArguments(AstFuncArguments visitor) {
        return null;
    }

    @Override
    public String visitAstFuncExpr(AstFuncExpr visitor) {
        return null;
    }

    @Override
    public String visitAstPrimaryExpr(AstPrimaryExpr visitor) {
        return null;
    }

    public String visitAstFunctionName(AstFunctionName visitor) {
        return visitor.toString();
    }

    public String visitAstSystemFunction(AstSystemFunction visitor) {
        StringBuilder builder = new StringBuilder();
        builder.append(".").append(visitor.child(0).toString()).append("(");
        for (int i = 1; i < visitor.getChildren().size(); i++) {
            builder.append(visitor.child(i).accept(this));
        }
        return builder.append(")").toString();
    }

    @Override
    public String visitAstProgram(AstProgram visitor) {
        return String.valueOf(visitor.program().accept(this));
    }
}
