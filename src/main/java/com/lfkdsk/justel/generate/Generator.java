/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.generate;

import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.generate.javagen.JavaCodeGenerator;
import com.lfkdsk.justel.generate.javagen.JavaSource;

/**
 * Basic Generator
 * Generate Source Code -> NEED:
 * 1. context => generate var
 * 2. Ast node => return expr
 *
 * @author liufengkai
 * Created by liufengkai on 2017/8/4.
 * @see JavaCodeGenerator
 */
public abstract class Generator {

    /**
     * binder - context
     */
    protected JustContext context;

    /**
     * binder - root node
     */
    protected AstNode rootNode;

    public Generator(JustContext context, AstNode rootNode) {
        this.context = context;
        this.rootNode = rootNode;
    }

    public abstract JavaSource generate();

    public Generator reset(JustContext context, AstNode rootNode) {
        this.context = context;
        this.rootNode = rootNode;

        return this;
    }

    public void clear() {
        this.context.clearVars();
    }
}
