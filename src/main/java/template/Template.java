package template;

import grammar.Grammar;
import parser.BnfCom;

/**
 * Created by liufengkai on 2017/7/18.
 */
public class Template {

    public static final BnfCom importGen = BnfCom.rule(Import.class)
            .sep("import")
            .ast(Grammar.string)
            .sep(";");

    public static final BnfCom packageGen = BnfCom.rule(Package.class)
            .sep("package")
            .ast(Grammar.string)
            .sep(";");

    public static final BnfCom functionGen = BnfCom.rule(Function.class)
            .ast(Grammar.string)
            .sep("public Object eval(JustContext context) {")
            .ast(Grammar.string)
            .sep("return")
            .ast(Grammar.string)
            .sep("}");

    public static final BnfCom classGen = BnfCom.rule(Class.class)
            .sep("public class")
            .ast(Grammar.string)
            .sep("implement Expression")
            .sep("{")
            .ast(functionGen)
            .sep("}");

    public static final BnfCom templateGen = BnfCom.rule(TemplateGen.class)
            .ast(packageGen)
            .repeat(importGen)
            .ast(classGen);

    public static void main(String[] args) {

    }
}
