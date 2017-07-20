package com.lfkdsk.justel.template.dom;

import com.lfkdsk.justel.context.JustContext;

/**
 * Basic DomComponent.
 * Created by liufengkai on 2017/7/18.
 */
public interface DomComponent {

    /**
     * Generate-Code
     *
     * @param context Vars-Context
     * @param builder Builder
     * @return return the builder in generateCode
     */
    StringBuilder generateCode(JustContext context, StringBuilder builder);

    /**
     * isValidate
     *
     * @param context Vars-Context
     * @return is validate
     */
    boolean isValidate(JustContext context);
}
