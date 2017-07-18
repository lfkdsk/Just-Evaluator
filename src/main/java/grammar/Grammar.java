package grammar;

import literal.StringLiteral;
import parser.BnfCon;

/**
 * Created by liufengkai on 2017/7/18.
 */
public class Grammar {
    public static final BnfCon string = BnfCon.rule().string(StringLiteral.class);

    public static final BnfCon expr = BnfCon.rule();

}
