package com.lfkdsk.justel.literal;

import com.lfkdsk.justel.exception.ParseException;
import com.lfkdsk.justel.token.NumberToken;
import com.lfkdsk.justel.token.Token;

/**
 * Number Literal.
 * We support two kinds of Number Value :
 * - Long Value
 * - Integer Value
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/24.
 */
public class NumberLiteral extends Literal {

    /**
     * Inner number
     */
    private Number number;

    public NumberLiteral(Token token) {
        super(token);

        if (token instanceof NumberToken) {
            this.number = ((NumberToken) token).getNumberValue();
        } else {
            throw new ParseException("UnSupport number value where token " +
                    "type is not NumberToken");
        }
    }

    @Override
    public String toString() {
        switch (token.getTag()) {
            case Token.INTEGER:
                return String.valueOf(number.intValue());
            case Token.LONG:
                return String.valueOf(number.longValue());
            default:
                return "undefine number literal";
        }
    }
}
