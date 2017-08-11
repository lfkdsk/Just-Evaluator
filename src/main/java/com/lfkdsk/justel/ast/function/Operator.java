/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.ast.function;

import com.lfkdsk.justel.ast.base.AstLeaf;
import com.lfkdsk.justel.token.Token;

/**
 * Operator
 * eq: factor { OP factor }
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/26.
 */
public class Operator extends AstLeaf {

    public Operator(Token token) {
        super(token);
    }

    public String operator() {
        return getText();
    }

    public String getText() {
        return token.getText();
    }
}
