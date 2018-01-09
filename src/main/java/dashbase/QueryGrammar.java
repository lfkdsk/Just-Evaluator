package dashbase;

import com.lfkdsk.justel.ast.tree.AstPrimaryExpr;
import com.lfkdsk.justel.ast.tree.AstProgram;
import com.lfkdsk.justel.literal.BoolLiteral;
import com.lfkdsk.justel.literal.IDLiteral;
import com.lfkdsk.justel.literal.NumberLiteral;
import com.lfkdsk.justel.literal.StringLiteral;
import com.lfkdsk.justel.parser.BnfCom;
import dashbase.ast.*;
import lombok.Getter;

import static com.lfkdsk.justel.parser.BnfCom.rule;
import static com.lfkdsk.justel.token.ReservedToken.reservedToken;
import static com.lfkdsk.justel.token.Token.EOL;

public class QueryGrammar {

    private BnfCom label0 = rule();


    ///////////////////////////////////////////////////////////////////////////
    // value
    ///////////////////////////////////////////////////////////////////////////

    private BnfCom number = rule().number(NumberLiteral.class);

    private BnfCom id = rule().identifier(IDLiteral.class, reservedToken);

    private BnfCom string = rule().string(StringLiteral.class);

    private BnfCom bool = rule().bool(BoolLiteral.class);


    ///////////////////////////////////////////////////////////////////////////
    // primary value := number | id | string | boolean
    ///////////////////////////////////////////////////////////////////////////

    @Getter
    private BnfCom primary = rule(AstPrimary.class).or(
            number,
            string,
            bool
    );

    ///////////////////////////////////////////////////////////////////////////
    // value label := primary
    ///////////////////////////////////////////////////////////////////////////

    private BnfCom valueLabel = rule(AstValueLabel.class).ast(primary);

    ///////////////////////////////////////////////////////////////////////////
    // inner label := label name : label
    ///////////////////////////////////////////////////////////////////////////

    @Getter
    private BnfCom innerLabel = rule(AstInnerLabelExpr.class).ast(string).token(":").ast(label0);

    ///////////////////////////////////////////////////////////////////////////
    // inner label list := innerLabel [, innerLabel] *
    ///////////////////////////////////////////////////////////////////////////

    private BnfCom innerLabelList = rule(AstLabelList.class).ast(innerLabel).repeat(
            rule().sep(",").repeat(innerLabel)
    );

    ///////////////////////////////////////////////////////////////////////////
    // label list := label [, label] *
    ///////////////////////////////////////////////////////////////////////////

    private BnfCom labelList = rule(AstPrimaryList.class).ast(label0).repeat(
            rule().sep(",").repeat(label0)
    );

    ///////////////////////////////////////////////////////////////////////////
    // object label := {
    //      label, label
    // }
    ///////////////////////////////////////////////////////////////////////////

    @Getter
    private BnfCom objectLabel = rule(AstObjectLabel.class).token("{")
                                                           .maybe(innerLabelList)
                                                           .token("}");

    ///////////////////////////////////////////////////////////////////////////
    // array label := [
    //      label list
    // ]
    ///////////////////////////////////////////////////////////////////////////

    @Getter
    private BnfCom arrayLabel = rule(AstArrayLabel.class).token("[")
                                                          .maybe(labelList)
                                                          .token("]");

    ///////////////////////////////////////////////////////////////////////////
    // label := value | object | array
    ///////////////////////////////////////////////////////////////////////////

    @Getter
    private BnfCom label = label0.reset(AstLabelExpr.class).or(
            valueLabel,
            objectLabel,
            arrayLabel
    );

    ///////////////////////////////////////////////////////////////////////////
    // program = {
    //      labelList
    // } EOL (end of line)
    ///////////////////////////////////////////////////////////////////////////

    @Getter
    private BnfCom program = rule(AstQueryProgram.class).token("{").maybe(innerLabelList).token("}").sep(EOL);
}
