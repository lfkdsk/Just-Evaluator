package template;

import template.dom.DomCom;
import template.dom.Template;


/**
 * Simple Template Impl
 * Use fixed structure Ast to save the template
 * and we can use it to generateCode-code as template file
 * Created by liufengkai on 2017/7/18.
 */
public class TemplateImpl implements Template {

    private static final DomCom packageGen = DomCom.rule()
            .sep("package")
            .sep("com.lfkdsk.justel.generatecode;");

    private static final DomCom importGen = DomCom.rule()
            .sep("import")
            .sep("com.greenpineyu.fel.common.*;");

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
