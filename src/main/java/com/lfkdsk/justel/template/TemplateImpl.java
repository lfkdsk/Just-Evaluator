package com.lfkdsk.justel.template;

import com.lfkdsk.justel.template.dom.DomCom;
import com.lfkdsk.justel.template.dom.Template;


/**
 * Simple Template Impl
 * Use fixed structure Ast to save the com.lfkdsk.justel.template
 * and we can use it to generateCode-code as com.lfkdsk.justel.template file
 * Created by liufengkai on 2017/7/18.
 */
public class TemplateImpl implements Template {

    private static final DomCom packageGen = DomCom.rule()
            .sep("package")
            .sep("com.lfkdsk.justel.generatecode;");

    private static final DomCom importGen = DomCom.rule()
            .sep("import com.lfkdsk.justel.context.JustContext;")
            .sep("import com.lfkdsk.justel.expr.Expression;");

    private static final DomCom functionGen = DomCom.rule()
            .bind("${attrs}")
            .sep("public Object eval(JustContext context) {")
            .bind("${localVars}")
            .sep("return")
            .bind("${expression}")
            .sep("}");

    private static final DomCom classGen = DomCom.rule()
            .sep("public class")
            .bind("${className}")
            .sep("implement Expression")
            .sep("{")
            .append(functionGen)
            .sep("}");

    private static final DomCom templateGen = DomCom.rule()
            .append(packageGen)
            .append(importGen)
            .append(classGen);

    @Override
    public DomCom generateTemplate() {
        return templateGen;
    }
}
