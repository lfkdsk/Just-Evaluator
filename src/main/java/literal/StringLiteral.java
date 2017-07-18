package literal;

import ast.AstLeaf;
import context.JustContext;
import token.Token;

/**
 * Created by liufengkai on 2017/7/18.
 */
public class StringLiteral extends AstLeaf {

    public StringLiteral(Token token) {
        super(token);
    }

    public String value() {
        return token.getText();
    }

    @Override
    public Object eval(JustContext env) {
        return super.eval(env);
    }
}
