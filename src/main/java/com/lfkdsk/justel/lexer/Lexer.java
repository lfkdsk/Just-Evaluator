package com.lfkdsk.justel.lexer;

import com.lfkdsk.justel.token.Token;
import com.lfkdsk.justel.utils.collection.ArrayQueue;

import java.util.Queue;

/**
 * Lexer
 * File/String Lexer. We wont focus on
 * the Lexer is a real Lexer or just a
 * list message.
 * Created by liufengkai on 2017/7/18.
 */
@FunctionalInterface
public interface Lexer {

//    /**
//     * peek first serial nodes
//     *
//     * @param index first serial nodes
//     * @return Token
//     */
//    Token peek(int index);
//
//    /**
//     * get first node of list
//     *
//     * @return Token
//     */
//    Token read();


//    void reset(String expr);

    Queue<Token> scanner(String expr);

    /**
     * has more tokens in Lexer
     *
     * @return has more?
     */
    default boolean hasMore() {
        return true;
    }

    default Queue<Token> tokens() {
        return new ArrayQueue<>();
    }
}
