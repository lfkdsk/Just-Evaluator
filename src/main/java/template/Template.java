package template;

import context.JustMapContext;
import template.dom.DomCom;

/**
 * Created by liufengkai on 2017/7/18.
 */
public class Template {

    public static final DomCom packageGen = DomCom.rule()
            .sep("package")
            .sep("com.greenpineyu.fel.compile;");

    public static final DomCom importGen = DomCom.rule()
            .sep("import")
            .sep("com.greenpineyu.fel.common.*;");

    public static final DomCom functionGen = DomCom.rule()
            .bind("${attrs}")
            .sep("public Object eval(JustContext context) {")
            .bind("${localVars}")
            .sep("return")
            .bind("${expression}")
            .sep("}");

    public static final DomCom classGen = DomCom.rule()
            .sep("public class")
            .bind("${className}")
            .sep("implement Expression")
            .sep("{")
            .append(functionGen)
            .sep("}");

    public static final DomCom templateGen = DomCom.rule()
            .append(packageGen)
            .append(importGen)
            .append(classGen);

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
