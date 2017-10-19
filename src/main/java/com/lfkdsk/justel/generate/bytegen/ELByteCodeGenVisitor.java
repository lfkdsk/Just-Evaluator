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
import com.lfkdsk.justel.literal.BoolLiteral;
import com.lfkdsk.justel.literal.IDLiteral;
import com.lfkdsk.justel.literal.NumberLiteral;
import com.lfkdsk.justel.literal.StringLiteral;
import com.lfkdsk.justel.token.Token;
import com.lfkdsk.justel.visitor.AstVisitor;

import java.util.LinkedList;
import java.util.List;

import static com.lfkdsk.justel.generate.bytegen.ELCommand.CommandType.*;

@Deprecated
public class ELByteCodeGenVisitor implements AstVisitor<Boolean> {

    private List<ELCommand> commands;

    public ELByteCodeGenVisitor() {
        this.commands = new LinkedList<>();

    }

    @Override
    public Boolean visitAstLeaf(AstLeaf leaf) {
        return null;
    }

    @Override
    public Boolean visitAstList(AstList list) {
        return null;
    }

    @Override
    public Boolean visitBoolLiteral(BoolLiteral visitor) {
        return commands.add(new ELCommand(
                push,
                String.valueOf(Token.BOOLEAN),
                String.valueOf(visitor.value())));
    }

    @Override
    public Boolean visitIDLiteral(IDLiteral visitor) {
        return commands.add(new ELCommand(
                load,
                visitor.name()
        ));
    }

    @Override
    public Boolean visitNumberLiteral(NumberLiteral visitor) {
        return commands.add(new ELCommand(
                push,
                String.valueOf(visitor.token().getTag()),
                visitor.toString()
        ));
    }

    @Override
    public Boolean visitStringLiteral(StringLiteral visitor) {
        return commands.add(new ELCommand(
                push,
                String.valueOf(Token.STRING),
                visitor.literal()
        ));
    }

    @Override
    public Boolean visitExtendFunctionExpr(ExtendFunctionExpr extendFunctionExpr) {
        return commands.add(new ELCommand(
                load,
                String.valueOf(AstNode.FUNCTION_EXPR),
                extendFunctionExpr.funcName()
        ));
    }

    @Override
    public Boolean visitOperatorExpr(OperatorExpr operatorExpr) {
        return visitOperatorExpr(operatorExpr, 0);
    }

    private boolean visitOperatorExpr(OperatorExpr operatorExpr, int paramsCount) {
        operatorExpr.leftChild().accept(this);
        operatorExpr.rightChild().accept(this);

        return commands.add(new ELCommand(
                op,
                operatorExpr.operator().toString(),
                String.valueOf(paramsCount)
        ));
    }

    @Override
    public Boolean visitAmpersandOp(AmpersandOp visitor) {
        return visitOperatorExpr(visitor, 2);
    }

    @Override
    public Boolean visitAndOp(AndOp visitor) {
        return visitOperatorExpr(visitor, 2);
    }

    @Override
    public Boolean visitArrayIndexExpr(ArrayIndexExpr visitor) {
        return visitOperatorExpr(visitor, 2);
    }

    @Override
    public Boolean visitCondOp(CondOp visitor) {
        visitor.trueExpr().accept(this);
        visitor.falseExpr().accept(this);
        return null;
    }

    @Override
    public Boolean visitDivOp(DivOp visitor) {
        return visitOperatorExpr(visitor, 2);
    }

    @Override
    public Boolean visitDotExpr(DotExpr visitor) {
        return visitOperatorExpr(visitor, 2);
    }

    @Override
    public Boolean visitEqualOp(EqualOp visitor) {
        return visitOperatorExpr(visitor, 2);
    }

    @Override
    public Boolean visitGreaterThanEqualOp(GreaterThanEqualOp visitor) {
        return visitOperatorExpr(visitor, 2);
    }

    @Override
    public Boolean visitGreaterThanOp(GreaterThanOp visitor) {
        return visitOperatorExpr(visitor, 2);
    }

    @Override
    public Boolean visitLessThanEqualOp(LessThanEqualOp visitor) {
        return visitOperatorExpr(visitor, 2);
    }

    @Override
    public Boolean visitLessThanOp(LessThanOp visitor) {
        return visitOperatorExpr(visitor, 2);
    }

    @Override
    public Boolean visitMinusOp(MinusOp visitor) {
        return visitOperatorExpr(visitor, 2);
    }

    @Override
    public Boolean visitModOp(ModOp visitor) {
        return visitOperatorExpr(visitor, 2);
    }

    @Override
    public Boolean visitMulOp(MulOp visitor) {
        return visitOperatorExpr(visitor, 2);
    }

    @Override
    public Boolean visitOrOp(OrOp visitor) {
        return visitOperatorExpr(visitor, 2);
    }

    @Override
    public Boolean visitPlusOp(PlusOp visitor) {
        return visitOperatorExpr(visitor, 2);
    }

    @Override
    public Boolean visitUnEqualOp(UnEqualOp visitor) {
        return visitOperatorExpr(visitor, 2);
    }

    @Override
    public Boolean visitNegativePostfix(NegativePostfix visitor) {
        visitor.operand().accept(this);
        return commands.add(new ELCommand(
                op,
                "-",
                String.valueOf(1)
        ));
    }

    @Override
    public Boolean visitNotPostfix(NotPostfix visitor) {
        visitor.operand().accept(this);
        return commands.add(new ELCommand(
                op,
                "!",
                String.valueOf(1)
        ));
    }

    @Override
    public Boolean visitAstBinaryExpr(AstBinaryExpr visitor) {
        return null;
    }

    @Override
    public Boolean visitAstCondExpr(AstCondExpr visitor) {
        visitor.condExpr().accept(this);
        visitor.condOp().accept(this);

        return commands.add(new ELCommand(
                cond,
                String.valueOf(2)
        ));
    }

    @Override
    public Boolean visitAstFuncArguments(AstFuncArguments visitor) {
        return null;
    }

    @Override
    public Boolean visitAstFuncExpr(AstFuncExpr visitor) {
        visitor.funcArgs().accept(this);

        return commands.add(new ELCommand(
                call,
                visitor.funcName().toString(),
                visitor.funcArgs().toString()
        ));
    }

    @Override
    public Boolean visitAstPrimaryExpr(AstPrimaryExpr visitor) {
        int childCount = visitor.childCount();
        int nest = childCount - 2;

        while (visitor.hasPostfix(visitor, nest) && nest >= 0) {
            AstPostfixExpr postfixExpr = visitor.postfix(visitor, nest);

            if (nest == childCount - 2) {
                // to stack
                commands.add(new ELCommand(
                        postfix,
                        postfixExpr.postfix(),
                        visitor.operand(visitor).toString(),
                        postfixExpr.toString()
                ));

            } else {
                commands.add(new ELCommand(
                        postfix,
                        postfixExpr.postfix(),
                        "#1",
                        postfixExpr.toString()
                ));
            }
            nest--;
        }

        return null;
    }

    @Override
    public Boolean visitAstProgram(AstProgram visitor) {
        return (Boolean) visitor.program().accept(this);
    }

    public Boolean visit(AstProgram visitor) {
        return visitAstProgram(visitor);
    }

    public List<ELCommand> getCommands() {
        return commands;
    }
}
