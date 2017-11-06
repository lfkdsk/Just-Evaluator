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
import com.lfkdsk.justel.token.Token;
import com.lfkdsk.justel.utils.ObjectHelper;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * AST Leaf Node
 *
 * @author liufengkai
 *         Created by liufengkai on 17/7/11.
 */
public class AstLeaf extends AstNode {

    private static ArrayList<AstNode> empty = new ArrayList<>();

    protected Token token;

    public AstLeaf(@NotNull Token token) {
        super(token.getTag());
        this.token = ObjectHelper.requireNonNull(token, "token could not be null");
    }

    public Token token() {
        return token;
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
    public AstNode resetChild(int index, AstNode node) {
        throw new UnsupportedOperationException("Didn't support this method in Leaf");
    }

    @Override
    public int computeAstLevel() {
        return astLevel = 1;
    }

    @Override
    public String toString() {
        if (evalString == null) {
            evalString = token.getText();
        }

        return evalString;
    }

    public Object eval(JustContext env) {
        throw new EvalException("can not eval : " + toString(), this);
    }

    @Override
    public String compile(JustContext env) {
        return toString();
    }
}
