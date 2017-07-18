package template;

import grammar.Grammar;
import parser.BnfCon;

/**
 * Created by liufengkai on 2017/7/18.
 */
public class Template {

    public static final BnfCon importGen = BnfCon.rule(Import.class)
            .sep("import")
            .ast(Grammar.string)
            .sep(";");

    public static final BnfCon packageGen = BnfCon.rule(Package.class)
            .sep("package")
            .ast(Grammar.string)
            .sep(";");

    public static final BnfCon functionGen = BnfCon.rule(Function.class)
            .ast(Grammar.string)
            .sep("public Object eval(JustContext context) {")
            .ast(Grammar.string)
            .sep("return")
            .ast(Grammar.string)
            .sep("}");

    public static final BnfCon classGen = BnfCon.rule(Class.class)
            .sep("public class")
            .ast(Grammar.string)
            .sep("implement Expression")
            .sep("{")
            .ast(functionGen)
            .sep("}");

    public static final BnfCon templateGen = BnfCon.rule(TemplateGen.class)
            .ast(packageGen)
            .repeat(importGen)
            .ast(classGen);

    public static void main(String[] args) {

    }
}
