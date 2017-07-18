package template.dom;

import context.JustContext;

/**
 * Created by liufengkai on 2017/7/18.
 */
public class IgnoreDomComponent implements DomComponent {

    private String[] ignoreDomString;

    public IgnoreDomComponent(String... ignoreDomString) {
        this.ignoreDomString = ignoreDomString;
    }

    @Override
    public StringBuilder generate(JustContext context, StringBuilder builder) {
        for (String s : ignoreDomString) {
            builder.append(s).append(" ");
        }
        return builder;
    }

    @Override
    public boolean validation(JustContext context) {
        return true;
    }
}
