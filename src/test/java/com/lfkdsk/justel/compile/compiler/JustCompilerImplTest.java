package com.lfkdsk.justel.compile.compiler;

import com.lfkdsk.justel.compile.generate.JavaSource;
import com.lfkdsk.justel.context.JustMapContext;
import com.lfkdsk.justel.expr.Expression;
import org.junit.jupiter.api.Test;
import com.lfkdsk.justel.template.TemplateImpl;
import com.lfkdsk.justel.template.dom.DomCom;
import com.lfkdsk.justel.utils.GeneratedId;

/**
 * Created by liufengkai on 2017/7/20.
 */
class JustCompilerImplTest {
    @Test
    void compile() {
        DomCom templateGen = new TemplateImpl().generateTemplate();
        JustCompilerImpl compiler = new JustCompilerImpl();
        JustMapContext context = new JustMapContext();
        String className = "JustEL" + GeneratedId.generateAtomId();
        context.put("${attrs}", "@Override");
        context.put("${className}", className);
        context.put("${localVars}", "int i = 10;");
        context.put("${expression}", "0;");

        String sourceCode = templateGen.fakeGenerateString(context);

        Expression expr = compiler
                .compile(new JavaSource("com.lfkdsk.justel.generatecode", className, sourceCode));

        System.out.println(expr);
    }
}