package com.lfkdsk.justel.literal;

import com.lfkdsk.justel.ast.AstLeaf;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.token.Token;

/**
 * Created by liufengkai on 2017/7/18.
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
    return super.eval(env);
  }
}
