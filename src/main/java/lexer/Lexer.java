package lexer;

import token.Token;

/**
 * Lexer
 * File/String Lexer. We wont focus on
 * the Lexer is a real Lexer or just a
 * list message.
 * Created by liufengkai on 2017/7/18.
 */
public interface Lexer {

    /**
     * peek first serial nodes
     *
     * @param index first serial nodes
     * @return Token
     */
    Token peek(int index);

    /**
     * get first node of list
     *
     * @return Token
     */
    Token read();
}
