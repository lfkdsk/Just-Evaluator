package com.lfkdsk.justel.literal;

import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.token.Token;

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

    @Override
    public Object eval(JustContext env) {
        return super.eval(env);
    }
}
