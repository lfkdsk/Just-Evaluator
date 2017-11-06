package com.lfkdsk.justel.lattice;

import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.ast.operators.*;
import com.lfkdsk.justel.ast.postfix.NegativePostfix;
import com.lfkdsk.justel.ast.postfix.NotPostfix;
import com.lfkdsk.justel.ast.tree.AstBinaryExpr;
import com.lfkdsk.justel.ast.tree.AstCollection;
import com.lfkdsk.justel.ast.tree.AstPrimaryExpr;
import com.lfkdsk.justel.ast.tree.AstProgram;
import com.lfkdsk.justel.exception.ParseException;
import com.lfkdsk.justel.lexer.Lexer;
import com.lfkdsk.justel.literal.BoolLiteral;
import com.lfkdsk.justel.literal.IDLiteral;
import com.lfkdsk.justel.literal.NumberLiteral;
import com.lfkdsk.justel.literal.StringLiteral;
import com.lfkdsk.justel.parser.BnfCom;
import com.lfkdsk.justel.parser.JustParser;
import com.lfkdsk.justel.token.ReservedToken;
import org.jetbrains.annotations.NotNull;

import static com.lfkdsk.justel.lattice.ParserHelper.generateAst;
import static com.lfkdsk.justel.parser.BnfCom.Operators.LEFT;
import static com.lfkdsk.justel.parser.BnfCom.rule;
import static com.lfkdsk.justel.token.ReservedToken.*;

public class LatticeParserImpl implements JustParser {

    ///////////////////////////////////////////////////////////////////////////
    // Support Operators
    ///////////////////////////////////////////////////////////////////////////

    private final BnfCom.Operators operators = new BnfCom.Operators();

    private final BnfCom expr0 = rule();


    ///////////////////////////////////////////////////////////////////////////
    // base language type
    ///////////////////////////////////////////////////////////////////////////

    private final BnfCom number = rule().number(NumberLiteral.class);

    private final BnfCom id = rule().identifier(IDLiteral.class, reservedToken);

    private final BnfCom string = rule().string(StringLiteral.class);

    private final BnfCom bool = rule().bool(BoolLiteral.class);

    private final BnfCom collection = rule(AstCollection.class).sep("[").ast(string).maybe(rule().repeat(rule().sep(",").ast(string))).sep("]");

    ///////////////////////////////////////////////////////////////////////////
    // Value expr = number | id | string | boolean
    ///////////////////////////////////////////////////////////////////////////

    private final BnfCom value = rule(AstPrimaryExpr.class)
            .or(
                    rule().sep(LP_TOKEN).ast(expr0).sep(RP_TOKEN),
                    number,
                    id,
                    string,
                    bool,
                    collection
            );

    private final BnfCom factor = rule()
            .or(
                    rule(NegativePostfix.class).sep(ReservedToken.MINUS).ast(value),
                    rule(NotPostfix.class).repeat(rule().sep(LOGICAL_F_TOKEN)).ast(value),
                    value
            );

    ///////////////////////////////////////////////////////////////////////////
    // expr = factor { OP factor }
    ///////////////////////////////////////////////////////////////////////////

    private final BnfCom expr = expr0.expression(AstBinaryExpr.class, factor, operators);

    ///////////////////////////////////////////////////////////////////////////
    // postfix = | fun() | isXXX
    ///////////////////////////////////////////////////////////////////////////

    private BnfCom postfix = rule().or(
            rule().identifier(reservedToken).sep("(").sep(")"),
            rule().token("isEmpty")
    );

    ///////////////////////////////////////////////////////////////////////////
    // "xxx.xxx" meta-circle interpreter
    ///////////////////////////////////////////////////////////////////////////

    private BnfCom dot = rule(DotExpr.class).sep(DOT_TOKEN).identifier(reservedToken);

    ///////////////////////////////////////////////////////////////////////////
    // program = expr EOL (end of line)
    ///////////////////////////////////////////////////////////////////////////

    private final BnfCom program = rule(AstProgram.class).ast(expr).sep(EOL);

    public LatticeParserImpl() {
        reservedToken.add(EOL);

        string.option(postfix);
        id.repeat(dot);

        insertOperators(LOGICAL_OR_TOKEN, 12, LEFT, OrOp.class);
        insertOperators(LOGICAL_AND_TOKEN, 11, LEFT, AndOp.class);

        insertOperators(EQ_TOKEN, 7, LEFT, EqualOp.class);
        insertOperators(UQ_TOKEN, 7, LEFT, UnEqualOp.class);

        insertOperators(GT_TOKEN, 6, LEFT, GreaterThanOp.class);
        insertOperators(GTE_TOKEN, 6, LEFT, GreaterThanEqualOp.class);
        insertOperators(LT_TOKEN, 6, LEFT, LessThanOp.class);
        insertOperators(LTE_TOKEN, 6, LEFT, LessThanEqualOp.class);

        // TODO lattice 默认语法并没有基础的运算 如果需要再加上

//        insertOperators(AMPERSAND_TOKEN, 8, LEFT, AmpersandOp.class);

//        insertOperators(PLUS, 4, LEFT, PlusOp.class);
//        insertOperators(MINUS, 4, LEFT, MinusOp.class);

//        insertOperators(DIV, 3, LEFT, DivOp.class);
//        insertOperators(MUL, 3, LEFT, MulOp.class);
//        insertOperators(MOD, 3, LEFT, ModOp.class);

        insertOperators(LOGICAL_F_TOKEN, 2, LEFT, NotPostfix.class);
    }


    /**
     * insert new operator
     *
     * @param funName   function name
     * @param pres      pres level
     * @param leftAssoc left / right
     * @param operator  operator clazz
     * @see com.lfkdsk.justel.parser.BnfCom.Operators
     */
    public void insertOperators(@NotNull final String funName,
                                final int pres,
                                final boolean leftAssoc,
                                @NotNull final Class<? extends AstNode> operator) {

        operators.add(funName, pres, leftAssoc, operator);
    }

    @Override
    public AstNode parser(Lexer lexer) throws ParseException {
        return generateAst(program.parse(lexer), operators);
    }
}
