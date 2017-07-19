package template.dom;

import context.JustContext;

/**
 * Created by liufengkai on 2017/7/19.
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
