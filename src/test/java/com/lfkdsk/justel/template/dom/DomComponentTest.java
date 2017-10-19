package com.lfkdsk.justel.template.dom;

import org.junit.jupiter.api.Test;

import static com.lfkdsk.justel.generate.javagen.JavaSource.GENERATE_DEFAULT_PACKAGE;

/**
 * Created by liufengkai on 2017/9/4.
 */
class DomComponentTest {

    @Test
    void testComponent() {

        com.lfkdsk.justel.template.dom.DomCom templateGen = com.lfkdsk.justel.template.dom.DomCom.rule()
                                                                                                 .append(com.lfkdsk.justel.template.dom.DomCom.rule()
                                                                                                                                              .sep("package")
                                                                                                                                              .sep(GENERATE_DEFAULT_PACKAGE + ";"))
                                                                                                 .append(com.lfkdsk.justel.template.dom.DomCom.rule()
                                                                                                                                              .sep("import com.lfkdsk.justel.context.JustContext;")
                                                                                                                                              .sep("import com.lfkdsk.justel.eval.Expression;"))
                                                                                                 .append(com.lfkdsk.justel.template.dom.DomCom.rule()
                                                                                                                                              .sep("public class")
                                                                                                                                              .bind("${className}")
                                                                                                                                              .sep("implements Expression ")
                                                                                                                                              .sep("{")
                                                                                                                                              .append(com.lfkdsk.justel.template.dom.DomCom.rule()
                                                                                                                                                                                           .bind("${attrs}")
                                                                                                                                                                                           .sep("@Override public Object eval(JustContext context) {")
                                                                                                                                                                                           .bind("${localVars}")
                                                                                                                                                                                           .sep("return")
                                                                                                                                                                                           .bind("${expression}")
                                                                                                                                                                                           .sep(";}"))
                                                                                                                                              .sep("}"));


        templateGen.randomVal()
                   .nop("*****")
                   .des("lfkdsk",
                           "lfkdsk")
                   .fakeGenerateComponent();


    }
}