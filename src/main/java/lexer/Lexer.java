package lexer;

import token.Token;

/**
 * Created by liufengkai on 2017/7/18.
 */
public interface Lexer {

    Token peek(int index);

    Token read();
}
