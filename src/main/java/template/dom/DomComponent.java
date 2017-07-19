package template.dom;

import context.JustContext;

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
     * @return return the builder in generate
     */
    StringBuilder generate(JustContext context, StringBuilder builder);

    /**
     * isValidate
     *
     * @param context Vars-Context
     * @return is validate
     */
    boolean isValidate(JustContext context);
}
