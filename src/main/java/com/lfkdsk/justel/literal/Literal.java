package com.lfkdsk.justel.literal;

import com.lfkdsk.justel.ast.base.AstLeaf;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.exception.UnSupportMethodException;
import com.lfkdsk.justel.token.Token;

/**
 * Literal is an AstLeaf.
 * Literal is an wrapper of LiterToken.
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/22.
 */
public abstract class Literal extends AstLeaf {

    public Literal(Token token) {
        super(token);
    }

    public String name() {
        return token.getText();
    }

    @Override
    public Object eval(JustContext env) {
        throw new UnSupportMethodException("Cannot call abstract literal " + token.toString());
    }
}
