package com.lfkdsk.justel.template.dom;

import com.lfkdsk.justel.context.JustContext;

/**
 * Bind Alternate Structure In AST
 * Usage:
 * DomCom.bind("${localVars}")
 * We bind a var block in com.lfkdsk.justel.template file and it will
 * be replace in structure in generateCode code.
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/18.
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
     * generateCode local level code
     *
     * @param context var-com.lfkdsk.justel.context
     * @param builder string-append-builder
     * @return appended string builder
     */
    @Override
    public StringBuilder generateCode(JustContext context,
                                      StringBuilder builder) {
        return builder.append(context.get(arg).toString()).append(" ");
    }

    @Override
    public boolean isValidate(JustContext context) {
        return context.contain(arg);
    }
}
