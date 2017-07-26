/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.ast.base;

import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.eval.Evalable;

import java.util.Iterator;

/**
 * AST TREE NODE 抽象语法树的通用接口
 * Created by liufengkai on 16/7/11.
 */
public abstract class AstNode implements Iterable<AstNode>,
        Evalable {

    private final int tag;

    public static final int AMPERSAND_OP = 600;

    public static final int PRIMARY_EXPR = 601;

    public static final int BINARY_EXPR = 602;

    public static final int FUNC_ARGUMENT_EXPR = 603;

    public static final int ARRAY_INDEX_OP = 604;

    public static final int BIT_WISE_OP = 605;

    public static final int DOT_OP = 606;

    public static final int EQUAL_OP = 607;

    public static final int NEGATIVE_OP = 608;

    public static final int NOT_OP = 609;

    public static final int UN_EQUAL_OP = 610;

    public AstNode(int tag) {
        this.tag = tag;
    }

    /**
     * 获取指定子节点
     *
     * @param index 索引
     * @return 子节点
     */
    public abstract AstNode child(int index);

    /**
     * 返回子节点迭代器
     *
     * @return 迭代器
     */
    public abstract Iterator<AstNode> children();

    /**
     * 子节点数目
     *
     * @return count
     */
    public abstract int childCount();

    /**
     * 位置描述
     *
     * @return location
     */
    public abstract String location();

    public Iterator<AstNode> iterator() {
        return children();
    }

    public Object eval(JustContext context) {
        return this;
    }
}