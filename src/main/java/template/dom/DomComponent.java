package template.dom;

import context.JustContext;

/**
 * Created by liufengkai on 2017/7/18.
 */
public interface DomComponent {

    StringBuilder generate(JustContext context, StringBuilder builder);

    boolean validation(JustContext context);
}
