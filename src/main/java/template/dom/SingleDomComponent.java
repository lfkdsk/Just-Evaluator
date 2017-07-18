package template.dom;

import context.JustContext;

/**
 * Created by liufengkai on 2017/7/18.
 */
public class SingleDomComponent implements DomComponent {

    private String arg;

    public SingleDomComponent(String arg) {
        this.arg = arg;
    }

    @Override
    public StringBuilder generate(JustContext context,
                                  StringBuilder builder) {
        return builder.append(context.get(arg).toString());
    }

    @Override
    public boolean validation(JustContext context) {
        return context.contain(arg);
    }
}
