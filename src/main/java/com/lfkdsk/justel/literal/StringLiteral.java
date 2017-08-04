package com.lfkdsk.justel.literal;

import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.token.Token;

import static com.lfkdsk.justel.utils.TypeUtils.isNull;

/**
 * String Literal =>
 * - "lfkdsk"
 * - "\"lfkdsk\""
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/18.
 */
public class StringLiteral extends Literal {

    public StringLiteral(Token token) {
        super(token);
    }

    public String value() {
        return token.getText();
    }

    @Override
    public Object eval(JustContext env) {
        if (!isNull(value())) {
            return value();
        }

        return super.eval(env);
    }

    @Override
    public String compile(JustContext env) {
        return "\"" + value() + "\"";
    }
}
