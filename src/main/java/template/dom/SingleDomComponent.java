package template.dom;

import context.JustContext;

/**
 * Bind Alternate Structure In AST
 * Usage:
 * DomCom.bind("${localVars}")
 * We bind a var block in template file and it will
 * be replace in structure in generate code.
 * Created by liufengkai on 2017/7/18.
 */
public class SingleDomComponent implements DomComponent {

    /**
     * var name eg: ${localVars} which will be
     * never changed in life-circle
     */
    private final String arg;

    SingleDomComponent(String arg) {
        this.arg = arg;
    }

    /**
     * generate local level code
     *
     * @param context var-context
     * @param builder string-append-builder
     * @return appended string builder
     */
    @Override
    public StringBuilder generate(JustContext context,
                                  StringBuilder builder) {
        return builder.append(context.get(arg).toString()).append(" ");
    }

    @Override
    public boolean isValidate(JustContext context) {
        return context.contain(arg);
    }
}
