package com.lfkdsk.justel.generate.bytegen;

import com.lfkdsk.justel.ast.base.AstLeaf;
import com.lfkdsk.justel.ast.base.AstList;
import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.ast.function.ExtendFunctionExpr;
import com.lfkdsk.justel.ast.function.OperatorExpr;
import com.lfkdsk.justel.ast.operators.*;
import com.lfkdsk.justel.ast.postfix.NegativePostfix;
import com.lfkdsk.justel.ast.postfix.NotPostfix;
import com.lfkdsk.justel.ast.tree.*;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.generate.javagen.Var;
import com.lfkdsk.justel.literal.BoolLiteral;
import com.lfkdsk.justel.literal.IDLiteral;
import com.lfkdsk.justel.literal.NumberLiteral;
import com.lfkdsk.justel.literal.StringLiteral;
import com.lfkdsk.justel.utils.GeneratedId;
import com.lfkdsk.justel.visitor.AstVisitor;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class ELByteCodeGenVisitor implements AstVisitor<String> {

    private JustContext env;

    private List<ELCommand> commands;

    private Stack<Var> varStack;

    public ELByteCodeGenVisitor(@NotNull JustContext env) {
        this.env = env;
        this.commands = new LinkedList<>();
        this.varStack = new Stack<>();
    }

    @Override
    public String visitAstLeaf(AstLeaf leaf) {
        String tmp = "t" + GeneratedId.generateAtomId();
        varStack.push(new Var(tmp, leaf.token().toString()));

        return tmp;
    }

    @Override
    public String visitAstList(AstList list) {

        for (AstNode astNode : list) {
            astNode.accept(this);
        }

        return null;
    }

    @Override
    public String visitBoolLiteral(BoolLiteral visitor) {
        return null;
    }

    @Override
    public String visitIDLiteral(IDLiteral visitor) {
        return null;
    }

    @Override
    public String visitNumberLiteral(NumberLiteral visitor) {
        return null;
    }

    @Override
    public String visitStringLiteral(StringLiteral visitor) {
        return null;
    }

    @Override
    public String visitExtendFunctionExpr(ExtendFunctionExpr extendFunctionExpr) {
        return null;
    }

    @Override
    public String visitOperatorExpr(OperatorExpr operatorExpr) {
        return null;
    }

    @Override
    public String visitAmpersandOp(AmpersandOp visitor) {
        return null;
    }

    @Override
    public String visitAndOp(AndOp visitor) {
        return null;
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
        return null;
    }

    @Override
    public String visitGreaterThanEqualOp(GreaterThanEqualOp visitor) {
        return null;
    }

    @Override
    public String visitGreaterThanOp(GreaterThanOp visitor) {
        return null;
    }

    @Override
    public String visitLessThanEqualOp(LessThanEqualOp visitor) {
        return null;
    }

    @Override
    public String visitLessThanOp(LessThanOp visitor) {
        return null;
    }

    @Override
    public String visitMinusOp(MinusOp visitor) {
        return null;
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
        return null;
    }

    @Override
    public String visitPlusOp(PlusOp visitor) {
        return null;
    }

    @Override
    public String visitUnEqualOp(UnEqualOp visitor) {
        return null;
    }

    @Override
    public String visitNegativePostfix(NegativePostfix visitor) {
        return null;
    }

    @Override
    public String visitNotPostfix(NotPostfix visitor) {
        return null;
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

    @Override
    public String visitAstProgram(AstProgram visitor) {
        return (String) visitor.program()
                             .accept(this);
    }
}
