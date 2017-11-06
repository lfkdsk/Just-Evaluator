package com.lfkdsk.justel.lattice.visitor;

import com.lfkdsk.justel.ast.base.AstLeaf;
import com.lfkdsk.justel.ast.base.AstList;
import com.lfkdsk.justel.ast.function.ExtendFunctionExpr;
import com.lfkdsk.justel.ast.function.OperatorExpr;
import com.lfkdsk.justel.ast.operators.*;
import com.lfkdsk.justel.ast.postfix.NegativePostfix;
import com.lfkdsk.justel.ast.postfix.NotPostfix;
import com.lfkdsk.justel.ast.tree.*;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.exception.EvalException;
import com.lfkdsk.justel.lattice.LatticeParserImpl;
import com.lfkdsk.justel.lexer.JustLexerImpl;
import com.lfkdsk.justel.lexer.Lexer;
import com.lfkdsk.justel.literal.BoolLiteral;
import com.lfkdsk.justel.literal.IDLiteral;
import com.lfkdsk.justel.literal.NumberLiteral;
import com.lfkdsk.justel.literal.StringLiteral;
import com.lfkdsk.justel.parser.JustParser;
import com.lfkdsk.justel.token.Token;
import com.lfkdsk.justel.utils.visitor.AstVisitor;

import static com.lfkdsk.justel.utils.NumberUtils.castTokenValue;
import static com.lfkdsk.justel.utils.TypeUtils.*;

public class LatticeEvalVisitor implements AstVisitor<Object> {

    private final JustContext env;

    private final Lexer lexer;

    private final JustParser parser;

//    private final JustEL justEL;

    public LatticeEvalVisitor(JustContext env) {
        this.env = env;
        this.lexer = new JustLexerImpl();
        this.parser = new LatticeParserImpl();
    }

    @Override
    public Object visitAstLeaf(AstLeaf leaf) {
        throw new EvalException("can not eval : " + leaf.toString(), leaf);
    }

    @Override
    public Object visitAstList(AstList list) {
        throw new EvalException("can not eval : " + list.toString(), list);
    }

    @Override
    public Object visitBoolLiteral(BoolLiteral visitor) {
        return visitor.value() ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Object visitIDLiteral(IDLiteral visitor) {
        Object value = env.get(visitor.name());

        if (value == null) {
            // READ 如果在环境里面没有对应的东西那么这个东西就是一个字符串
            return visitor.name();
        } else {
            return value;
        }
    }

    @Override
    public Object visitNumberLiteral(NumberLiteral visitor) {
        return castTokenValue(visitor.numberToken());
    }

    @Override
    public Object visitStringLiteral(StringLiteral visitor) {
        if (!isNull(visitor.value())) {
            lexer.reset(visitor.value());
            return parser.parser(lexer);
        }

        throw new EvalException("environment has not value " + visitor.value(), visitor);
    }

    @Override
    public Object visitExtendFunctionExpr(ExtendFunctionExpr extendFunctionExpr) {
        throw new UnsupportedOperationException("un-support operator in lattice now");
    }

    @Override
    public Object visitOperatorExpr(OperatorExpr operatorExpr) {
        return operatorExpr.eval(env);
    }

    @Override
    public Object visitAmpersandOp(AmpersandOp visitor) {
        throw new UnsupportedOperationException("un-support operator in lattice now");
    }

    @Override
    public Object visitAndOp(AndOp visitor) {
        if (visitor.operator().tag() != Token.AND) {
            throw new EvalException("cannot invalidate operator && ", visitor);
        }

        Object leftValue = visitor.leftChild().eval(env);
        Object rightValue = visitor.rightChild().eval(env);

        // is boolean
        if (isBoolean(leftValue) && isBoolean(rightValue)) {
            return leftValue == Boolean.TRUE
                    && rightValue == Boolean.TRUE;
        }

        return visitOperatorExpr(visitor);
    }

    @Override
    public Object visitArrayIndexExpr(ArrayIndexExpr visitor) {
        throw new UnsupportedOperationException("un-support operator in lattice now");
    }

    @Override
    public Object visitCondOp(CondOp visitor) {
        throw new UnsupportedOperationException("un-support operator in lattice now");
    }

    @Override
    public Object visitDivOp(DivOp visitor) {
        throw new UnsupportedOperationException("un-support operator in lattice now");
    }

    @Override
    public Object visitDotExpr(DotExpr visitor) {
        throw new UnsupportedOperationException("un-support operator in lattice now");
    }

    @Override
    public Object visitEqualOp(EqualOp visitor) {
        Object left = visitor.leftChild().eval(env);
        Object right = visitor.rightChild().eval(env);

        // boolean == boolean
        if (isBoolean(left) && isBoolean(right)) {

            return left.equals(right);
        } else if (isNumber(left) && isNumber(right)) {

            // num == num
            return left.equals(right);
        } else if (isString(left) && isString(right)) {

            return left.equals(right);
        }

        return visitOperatorExpr(visitor);
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
        throw new UnsupportedOperationException("un-support operator in lattice now");
    }

    @Override
    public Object visitModOp(ModOp visitor) {
        throw new UnsupportedOperationException("un-support operator in lattice now");
    }

    @Override
    public Object visitMulOp(MulOp visitor) {
        throw new UnsupportedOperationException("un-support operator in lattice now");
    }

    @Override
    public Object visitOrOp(OrOp visitor) {
        return null;
    }

    @Override
    public Object visitPlusOp(PlusOp visitor) {
        throw new UnsupportedOperationException("un-support operator in lattice now");
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
        throw new UnsupportedOperationException("un-support operator in lattice now");
    }

    @Override
    public Object visitAstFuncArguments(AstFuncArguments visitor) {
        throw new UnsupportedOperationException("un-support operator in lattice now");
    }

    @Override
    public Object visitAstFuncExpr(AstFuncExpr visitor) {
        throw new UnsupportedOperationException("un-support operator in lattice now");
    }

    @Override
    public Object visitAstPrimaryExpr(AstPrimaryExpr visitor) {
        return null;
    }

    @Override
    public Object visitAstProgram(AstProgram visitor) {
        return visitor.program().accept(this);
    }


}
