package com.lfkdsk.justel.template.dom;

import org.junit.jupiter.api.Test;

import static com.lfkdsk.justel.compile.generate.JavaSource.GENERATE_DEFAULT_PACKAGE;

/**
 * Created by liufengkai on 2017/9/4.
 */
class DomComponentTest {

    @Test
    void testComponent() {

        DomCom templateGen = DomCom.rule()
                .append(DomCom.rule()
                        .sep("package")
                        .sep(GENERATE_DEFAULT_PACKAGE + ";"))
                .append(DomCom.rule()
                        .sep("import com.lfkdsk.justel.context.JustContext;")
                        .sep("import com.lfkdsk.justel.eval.Expression;"))
                .append(DomCom.rule()
                        .sep("public class")
                        .bind("${className}")
                        .sep("implements Expression ")
                        .sep("{")
                        .append(DomCom.rule()
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