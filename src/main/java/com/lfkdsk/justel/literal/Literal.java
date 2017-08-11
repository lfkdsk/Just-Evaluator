package com.lfkdsk.justel.literal;

import com.lfkdsk.justel.ast.base.AstLeaf;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.exception.UnSupportMethodException;
import com.lfkdsk.justel.token.Token;
import com.lfkdsk.justel.utils.GeneratedId;

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
        throw new UnSupportMethodException("Cannot eval abstract literal " + token.toString());
    }

    /**
     * Literal to Local Var
     *
     * @param env env
     * @return return compile
     * @see com.lfkdsk.justel.ast.tree.AstFuncArguments
     */
    public Object compileVar(JustContext env) {
        String var = "val" + GeneratedId.generateAtomId();
        Object varObj = eval(env);
        env.put(var, varObj);

        return var;
    }
}
