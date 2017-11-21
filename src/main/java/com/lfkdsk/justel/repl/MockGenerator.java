package com.lfkdsk.justel.repl;

import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.compile.generate.Generator;
import com.lfkdsk.justel.compile.generate.JavaCodeGenerator;
import com.lfkdsk.justel.compile.generate.JavaSource;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.template.TemplateImpl;
import com.lfkdsk.justel.template.dom.Template;

public class MockGenerator implements Generator {
    private JavaCodeGenerator javaCodeGenerator = new JavaCodeGenerator();
    private Template template = new MockTemplate();

    public static class MockTemplate extends TemplateImpl {
        MockTemplate() {
            importGen.reset(importGen.sep("import com.lfkdsk.justel.repl.ReplMethodHelper;"));
        }
    }


    @Override
    public JavaSource generate(JustContext context, AstNode rootNode) {
        javaCodeGenerator.setMTemplate(template.generateTemplate());
        return javaCodeGenerator.generate(context, rootNode);
    }
}
