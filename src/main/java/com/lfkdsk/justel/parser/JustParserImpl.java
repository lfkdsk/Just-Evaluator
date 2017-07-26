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
import com.lfkdsk.justel.ast.tree.AstBinaryExpr;
import com.lfkdsk.justel.ast.tree.AstFuncArguments;
import com.lfkdsk.justel.ast.tree.AstPrimaryExpr;
import com.lfkdsk.justel.exception.ParseException;
import com.lfkdsk.justel.lexer.Lexer;
import com.lfkdsk.justel.literal.BoolLiteral;
import com.lfkdsk.justel.literal.IDLiteral;
import com.lfkdsk.justel.literal.NumberLiteral;
import com.lfkdsk.justel.literal.StringLiteral;

import static com.lfkdsk.justel.parser.BnfCom.Operators.LEFT;
import static com.lfkdsk.justel.parser.BnfCom.rule;
import static com.lfkdsk.justel.token.ReservedToken.reservedToken;
import static com.lfkdsk.justel.token.Token.EOL;

/**
 * Created by liufengkai on 2017/7/26.
 */
public class JustParserImpl implements JustParser {

    private BnfCom expr0 = rule();

    private BnfCom.Operators operators = new BnfCom.Operators();

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
                    rule().sep("(").ast(expr0).sep(")"),
                    number,
                    id,
                    string,
                    bool
            );

    ///////////////////////////////////////////////////////////////////////////
    // factor expr = - primary | ! primary | primary
    ///////////////////////////////////////////////////////////////////////////

    private BnfCom factor = rule()
            .or(
                    rule(NegativeExpr.class).sep("-").ast(primary),
                    rule(NotExpr.class).sep("!").ast(primary),
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
            .ast(expr).repeat(rule().sep(",").ast(expr));

    ///////////////////////////////////////////////////////////////////////////
    // postfix = ( args | null ) | . id | [ expr ]
    ///////////////////////////////////////////////////////////////////////////

    private BnfCom postfix = rule()
            .or(
                    rule().sep("(").maybe(args).sep(")"),
                    rule(DotExpr.class).sep(".").identifier(reservedToken),
                    rule(ArrayIndexExpr.class).sep("[").ast(expr).sep("]")
            );

    ///////////////////////////////////////////////////////////////////////////
    // program = expr EOL (end of line)
    ///////////////////////////////////////////////////////////////////////////

    private BnfCom program = rule().ast(expr).sep(EOL);


    JustParserImpl() {

        // primary { postfix }
        primary.repeat(postfix);

        reservedToken.add(EOL);

        operators.add("+", 4, LEFT, PlusOp.class);
        operators.add("==", 7, LEFT, EqualOp.class);
        operators.add("!=", 7, LEFT, UnEqualOp.class);
    }

    private void subChangeBinaryNode(AstNode parent) {
        for (int i = 0; i < parent.childCount(); i++) {
            AstNode child = parent.child(i);

            if (child instanceof AstBinaryExpr) {
                child = BnfCom.resetAstExpr((AstBinaryExpr) child, operators);
                parent.setChild(i, child);
            }
            subChangeBinaryNode(child);
        }
    }

    private AstNode changeBinaryNode(AstNode unchecked) {
        if (unchecked instanceof AstBinaryExpr) {
            unchecked = BnfCom.resetAstExpr((AstBinaryExpr) unchecked, operators);
        }
        subChangeBinaryNode(unchecked);
        return unchecked;
    }

    @Override
    public AstNode parser(Lexer lexer) throws ParseException {
        return changeBinaryNode(program.parse(lexer));
    }
}
