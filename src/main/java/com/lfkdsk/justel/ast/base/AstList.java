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

import java.util.Iterator;
import java.util.List;

/**
 * AST 抽象语法树的枝干
 *
 * @author liufengkai Created by liufengkai on 16/7/11.
 */
public class AstList extends AstNode {

    // break 存储位置
    public int afterPoint = 0;

    public static AstList NullList = new AstList(null, -1);

    public static AstList EnClosingList = NullList;

    public static AstList saveList = null;

    protected List<AstNode> children;

    public AstList(List<AstNode> children, int tag) {
        super(tag);
        this.children = children;
    }

    @Override
    public AstNode child(int index) {
        return children.get(index);
    }

    @Override
    public Iterator<AstNode> children() {
        return children.iterator();
    }

    @Override
    public int childCount() {
        return children.size();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append('(');

        String sep = "";

        for (AstNode node : children) {
            builder.append(sep);
            sep = " ";
            builder.append(node.toString());
        }

        return builder.append(')').toString();
    }

    @Override
    public String location() {
        for (AstNode n : children) {
            String s = n.location();
            if (s != null) {
                return s;
            }
        }
        return null;
    }

    public Object eval(JustContext env) {
        throw new EvalException("can not com.lfkdsk.justel.eval : " + toString(), this);
    }
}
