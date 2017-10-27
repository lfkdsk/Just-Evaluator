package com.lfkdsk.justel.generate.bytecodegen;

import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.ast.operators.*;
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
import com.lfkdsk.justel.parser.BnfCom;
import com.lfkdsk.justel.parser.JustParser;
import org.jetbrains.annotations.NotNull;

import static com.lfkdsk.justel.parser.BnfCom.Operators.LEFT;
import static com.lfkdsk.justel.parser.BnfCom.rule;
import static com.lfkdsk.justel.token.ReservedToken.*;

public class MidLispParser implements JustParser {

    ///////////////////////////////////////////////////////////////////////////
    // Support Operators
    ///////////////////////////////////////////////////////////////////////////

    private BnfCom.Operators operators = new BnfCom.Operators();

    private BnfCom expr0 = rule();


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
                    expr0,
                    number,
                    id,
                    string,
                    bool
            );

    private BnfCom args = rule(AstFuncArguments.class).repeat(primary);

    private BnfCom expr = expr0.reset(AstBinaryExpr.class).sep("(").ast(primary).maybe(args).sep(")");

    private BnfCom program = rule(AstProgram.class).ast(expr).sep("\\n");


    public MidLispParser() {
        reservedToken.remove(PLUS);
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
        insertOperators(MOD, 3, LEFT, ModOp.class);

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
        return program.parse(lexer);
    }
}
