package template;

import context.JustMapContext;
import template.dom.DomCom;
import template.dom.Template;

import static template.dom.DomCom.rule;

/**
 * Simple Template Impl
 * Use fixed structure Ast to save the template
 * and we can use it to generateCode-code as template file
 * Created by liufengkai on 2017/7/18.
 */
public class TemplateImpl implements Template {

    private static final DomCom packageGen = rule()
            .sep("package")
            .sep("com.lfkdsk.justel.generatecode;");

    private static final DomCom importGen = rule()
            .sep("import")
            .sep("com.greenpineyu.fel.common.*;");

    private static final DomCom functionGen = rule()
            .bind("${attrs}")
            .sep("public Object eval(JustContext context) {")
            .bind("${localVars}")
            .sep("return")
            .bind("${expression}")
            .sep("}");

    private static final DomCom classGen = rule()
            .sep("public class")
            .bind("${className}")
            .sep("implement Expression")
            .sep("{")
            .append(functionGen)
            .sep("}");

    private static final DomCom templateGen = rule()
            .append(packageGen)
            .append(importGen)
            .append(classGen);

    @Override
    public DomCom generateTemplate() {
        return templateGen;
    }

    public static void main(String[] args) {
        JustMapContext context = new JustMapContext();
        context.put("${attrs}", "@Override");
        context.put("${className}", "FakeName");
        context.put("${localVars}", "int i = 10;");
        context.put("${expression}", "0;");
        for (int j = 0; j < 10; j++) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 100 * 100; i++) {
                templateGen.fakeGenerateString(context);
            }
            long end = System.currentTimeMillis();
            System.out.println(end - start);
        }
    }
}
