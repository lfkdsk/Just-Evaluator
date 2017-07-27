package com.lfkdsk.justel.template.dom;

import com.lfkdsk.justel.context.JustContext;

/**
 * Nop Component.
 * nopString will not be added to builder.
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/19.
 */
public class NopComponent implements DomComponent {

    public NopComponent(String... nopString) {
        // nopString useless
    }

    @Override
    public StringBuilder generateCode(JustContext context, StringBuilder builder) {
        return builder;
    }

    @Override
    public boolean isValidate(JustContext context) {
        return true;
    }
}
