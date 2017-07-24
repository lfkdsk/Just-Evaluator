package com.lfkdsk.justel.literal;

import com.lfkdsk.justel.token.Token;

/**
 * Created by liufengkai on 2017/7/24.
 */
public class NumberLiteral extends Literal {

    private Long longNumber = null;

    private Integer intNumber = null;

    public NumberLiteral(Token token) {
        super(token);
    }

}
