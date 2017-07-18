package template;

import org.junit.jupiter.api.Test;

/**
 * Created by liufengkai on 2017/7/18.
 */
class TemplateTest {

    @Test
    void main() {
        String template = "package com.greenpineyu.fel.compile;\n" +
                "\n" +
                "import com.greenpineyu.fel.common.*;\n" +
                "import com.greenpineyu.fel.Expression;\n" +
                "import com.greenpineyu.fel.context.*;\n" +
                "//import org.apache.commons.lang3.ObjectUtils;\n" +
                "//import org.apache.commons.lang3.StringUtils;\n" +
                "\n" +
                "public class ${classname} implements Expression {\n" +
                "    ${attrs}\n" +
                "    public Object eval(FelContext context) {\n" +
                "        ${localVars}\n" +
                "        return ${expression};\n" +
                "    }\n" +
                "}\n";
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100 * 100; i++) {
            String src = template;
            src = StringUtils.replace(src, "${attrs}", "lfkdsk");
            src = StringUtils.replace(src, "${localVars}", "lfkdsk");
            src = StringUtils.replace(src, "${expression}", "lfkdsk");
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}