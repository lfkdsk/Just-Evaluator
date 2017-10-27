/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.generate.javagen;

import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.context.JustMapContext;
import com.lfkdsk.justel.generate.Generator;
import com.lfkdsk.justel.template.TemplateImpl;
import com.lfkdsk.justel.template.dom.DomCom;
import com.lfkdsk.justel.utils.GeneratedId;

import java.util.*;

/**
 * Java Code => Generator
 * AST + Template Tree => Java Source Code
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/20.
 */
public class JavaCodeGenerator extends Generator {

    private Set<Var> varSet = new HashSet<>();

    private static final DomCom mTemplate = new TemplateImpl().generateTemplate();

    public JavaCodeGenerator() {
        this(null, null);
    }

    public JavaCodeGenerator(JustContext context, AstNode rootNode) {
        super(context, rootNode);
    }


    protected String generateLocalVars() {
        varSet.clear();

        if (context == null) return "";
        StringBuilder builder = new StringBuilder();

        Collection<String> keySet = context.varsKeySet();
        for (String key : keySet) {
            Var var = new Var(key, context.get(key));
            varSet.add(var);
            builder.append(var.generateVarAssignCode());
        }

        List<String> commandSet = context.commandList();
        List<Integer> traceList = context.varTraceList();

//        for (int i = traceList.size() - 1; i >= 0; i--) {
//            Integer varHash = traceList.get(i);
//
//            if (commandSet.containsKey(varHash)) {
//                builder.append(commandSet.get(varHash));
//            }
//        }

        for (String command : commandSet) {
            builder.append(command);
        }

        return builder.toString();
    }

    @Override
    public JavaSource generate() {
        JustContext templateContext = new JustMapContext();
        String className = "JustEL" + GeneratedId.generateAtomId();

        templateContext.put("${className}", className);
        templateContext.put("${expression}", rootNode.compile(context));
        // after generate Ast -> generate local vars
        // some vars maybe latter than AST Compile
        templateContext.put("${localVars}", generateLocalVars());
        templateContext.put("${attrs}", "");

        return new JavaSource(JavaSource.GENERATE_DEFAULT_PACKAGE,
                className, mTemplate.fakeGenerateString(templateContext));
    }

    @Override
    public Generator reset(JustContext context, AstNode rootNode) {
        this.varSet.clear();

        return super.reset(context, rootNode);
    }

    @Override
    public void clear() {
        super.clear();
        this.varSet.clear();
    }
}
