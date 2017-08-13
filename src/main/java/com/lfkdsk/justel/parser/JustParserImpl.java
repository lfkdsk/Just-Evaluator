/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.parser;

import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.ast.operators.*;
import com.lfkdsk.justel.ast.postfix.NegativePostfix;
import com.lfkdsk.justel.ast.postfix.NotPostfix;
import com.lfkdsk.justel.ast.tree.AstBinaryExpr;
import com.lfkdsk.justel.ast.tree.AstFuncArguments;
import com.lfkdsk.justel.ast.tree.AstPrimaryExpr;
import com.lfkdsk.justel.ast.tree.AstProgram;
import com.lfkdsk.justel.exception.ParseException;
import com.lfkdsk.justel.lexer.Lexer;
import com.lfkdsk.justel.literal.BoolLiteral;
import com.lfkdsk.justel.literal.IDLiteral;
import com.lfkdsk.justel.literal.NumberLiteral;
import com.lfkdsk.justel.literal.StringLiteral;
import com.lfkdsk.justel.token.ReservedToken;

import static com.lfkdsk.justel.token.ReservedToken.*;
import static com.lfkdsk.justel.token.Token.EOL;
import static com.lfkdsk.justel.parser.BnfCom.Operators.LEFT;
import static com.lfkdsk.justel.parser.BnfCom.rule;

/**
 * Just Parser Implementation, Support Follow BNF Grammars:
 * - number: int, long, double
 * - id:
 * - string: "lfkdsk"
 * - bool: true | false
 * - primary: (expr) | number | id | string | bool { postfix }
 * - factor: !primary | -primary | primary
 * - expr: factor { OP  factor }
 * - args: expr, expr, expr
 * - postfix: ( args | null ) | .id | [ expr ]
 * - program: expr EOL
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/26.
 */
public class JustParserImpl implements JustParser {

    private BnfCom expr0 = rule();

    protected BnfCom.Operators operators = new BnfCom.Operators();

    ///////////////////////////////////////////////////////////////////////////
    // base language type
    ///////////////////////////////////////////////////////////////////////////

    private BnfCom number = rule().number(NumberLiteral.class);

    private BnfCom id = rule().identifier(IDLiteral.class, reservedToken);

    private BnfCom string = rule().string(StringLiteral.class);

    private BnfCom bool = rule().bool(BoolLiteral.class);

    ///////////////////////////////////////////////////////////////////////////
    // primary expr = ( expr ) | number | id | string | boolean
    ///////////////////////////////////////////////////////////////////////////

    private BnfCom primary = rule(AstPrimaryExpr.class)
            .or(
                    rule().sep(LP_TOKEN).ast(expr0).sep(RP_TOKEN),
                    number,
                    id,
                    string,
                    bool
            );

    ///////////////////////////////////////////////////////////////////////////
    // factor = - primary | ! primary | primary
    ///////////////////////////////////////////////////////////////////////////

    private BnfCom factor = rule()
            .or(
                    rule(NegativePostfix.class).sep(ReservedToken.MINUS).ast(primary),
                    rule(NotPostfix.class).repeat(rule().sep(LOGICAL_F_TOKEN)).ast(primary),
                    primary
            );

    ///////////////////////////////////////////////////////////////////////////
    // expr = factor { OP factor }
    ///////////////////////////////////////////////////////////////////////////

    private BnfCom expr = expr0.expression(AstBinaryExpr.class, factor, operators);

    ///////////////////////////////////////////////////////////////////////////
    // args = expr0, expr1, expr2
    ///////////////////////////////////////////////////////////////////////////

    private BnfCom args = rule(AstFuncArguments.class)
            .ast(expr).repeat(rule().sep(COMMA).ast(expr));

    ///////////////////////////////////////////////////////////////////////////
    // postfix = ( args | null ) | . id | [ expr ]
    ///////////////////////////////////////////////////////////////////////////

    private BnfCom postfix = rule().or(
            rule().sep(LP_TOKEN).maybe(args).sep(RP_TOKEN),
            rule(DotExpr.class).sep(DOT_TOKEN).identifier(reservedToken),
            rule(ArrayIndexExpr.class).sep(LM_TOKEN).ast(expr).sep(RM_TOKEN)
    );

    ///////////////////////////////////////////////////////////////////////////
    // cond = ? expr : expr
    ///////////////////////////////////////////////////////////////////////////

    private BnfCom cond = rule(CondOp.class).sep(QUESTION_TOKEN).ast(expr).sep(COLON_TOKEN).ast(expr);

    ///////////////////////////////////////////////////////////////////////////
    // program = expr EOL (end of line)
    ///////////////////////////////////////////////////////////////////////////

    private BnfCom program = rule(AstProgram.class).ast(expr).sep(EOL);

    public JustParserImpl() {

        // primary { postfix }
        primary.repeat(postfix);
        expr.repeat(cond);

        reservedToken.add(EOL);

        insertOperators(LOGICAL_OR_TOKEN, 12, LEFT, OrOp.class);
        insertOperators(LOGICAL_AND_TOKEN, 11, LEFT, AndOp.class);

        insertOperators(AMPERSAND_TOKEN, 8, LEFT, AmpersandOp.class);
        insertOperators(EQ_TOKEN, 7, LEFT, EqualOp.class);
        insertOperators(UQ_TOKEN, 7, LEFT, UnEqualOp.class);

        insertOperators(GT_TOKEN, 6, LEFT, GreaterThanOp.class);
        insertOperators(GTE_TOKEN, 6, LEFT, GreaterThanEqualOp.class);
        insertOperators(LT_TOKEN, 6, LEFT, LessThanOp.class);
        insertOperators(LTE_TOKEN, 6, LEFT, LessThanEqualOp.class);

        insertOperators(PLUS, 4, LEFT, PlusOp.class);
        insertOperators(MINUS, 4, LEFT, MinusOp.class);

        insertOperators(DIV, 3, LEFT, DivOp.class);
        insertOperators(MUL, 3, LEFT, MulOp.class);
        insertOperators(MOD, 3, LEFT, Mod.class);

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
    public void insertOperators(final String funName, final int pres,
                                final boolean leftAssoc, final Class<? extends AstNode> operator) {
        operators.add(funName, pres, leftAssoc, operator);
    }

    @Override
    public AstNode parser(Lexer lexer) throws ParseException {
        return generateAst(program.parse(lexer), operators);
    }
}
