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
import com.lfkdsk.justel.context.JustMapContext;
import com.lfkdsk.justel.template.TemplateImpl;
import com.lfkdsk.justel.template.dom.DomCom;
import com.lfkdsk.justel.utils.GeneratedId;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by liufengkai on 2017/7/20.
 */
public class JavaCodeGenerator extends Generator {

    private Set<Var> varSet = new HashSet<>();

    private static final DomCom mTemplate = new TemplateImpl().generateTemplate();

    public JavaCodeGenerator(JustContext context, AstNode rootNode) {
        super(context, rootNode);
    }


    private String generateLocalVars() {
        varSet.clear();

        if (context == null) return "";

        Collection<String> keySet = context.keySet();
        StringBuilder builder = new StringBuilder();
        for (String key : keySet) {
            Var var = new Var(key, context.get(key));
            varSet.add(var);
            builder.append(var.generateVarAssignCode());
        }

        return builder.toString();
    }

    @Override
    public JavaSource generate() {
        JustContext templateContext = new JustMapContext();
        String className = "JustEL" + GeneratedId.generateAtomId();
        templateContext.put("${attrs}", "@Override");
        templateContext.put("${className}", className);
        templateContext.put("${expression}", rootNode.compile(context));
        // after generate Ast -> generate local vars
        // some vars maybe latter than AST Compile
        templateContext.put("${localVars}", generateLocalVars());

        return new JavaSource(JavaSource.GENERATE_DEFAULT_PACKAGE,
                className, mTemplate.fakeGenerateString(templateContext));
    }
}
