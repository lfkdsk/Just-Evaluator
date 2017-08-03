/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.ast.base;

import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.exception.EvalException;
import com.lfkdsk.justel.exception.ParseException;
import com.lfkdsk.justel.token.Token;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * AST Leaf Node
 *
 * @author liufengkai
 *         Created by liufengkai on 16/7/11.
 */
public class AstLeaf extends AstNode {

    private static ArrayList<AstNode> empty = new ArrayList<>();

    protected Token token;

    public AstLeaf(Token token) {
        super(token.getTag());
        this.token = token;
    }

    @Override
    public AstNode child(int index) {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public Iterator<AstNode> children() {
        return empty.iterator();
    }

    @Override
    public int childCount() {
        return 0;
    }

    @Override
    public String location() {
        return "at line " + token.getLineNumber();
    }

    @Override
    public AstNode replaceChild(int index, AstNode node) {
        throw new ParseException("Didn't support this method in Leaf");
    }

    public Token token() {
        return token;
    }

    @Override
    public String toString() {
        return token.getText();
    }

    public Object eval(JustContext env) {
        throw new EvalException("can not eval : " + toString(), this);
    }
}
