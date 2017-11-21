/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.parser;

import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.exception.ParseException;
import com.lfkdsk.justel.token.Token;

import java.util.Queue;

/**
 * Just Parser Interface
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/26.
 */
@FunctionalInterface
public interface JustParser {


    /**
     * Parse with Lexer(Provide Tokens)
     *
     * @param lexer Tokens Lexer
     * @return Root Node of AST
     * @throws ParseException Parse
     */
    AstNode parser(Queue<Token> lexer) throws ParseException;
}
