package compile;

import context.JustContext;

/**
 * Created by liufengkai on 2017/7/18.
 */
public interface Compileable {
    StringBuilder compile(StringBuilder builder, JustContext context);
}
