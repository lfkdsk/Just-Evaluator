/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.compile.generate;

import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.context.JustContext;

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
@FunctionalInterface
public interface Generator {

    /**
     * Generate Source Code
     *
     * @param context  context-env
     * @param rootNode root-node
     * @return SourceNode
     */
    JavaSource generate(JustContext context, AstNode rootNode);
}
