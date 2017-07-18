package eval;

import context.JustContext;

/**
 * Created by liufengkai on 2017/7/18.
 */
public interface Evalable {
    Object eval(JustContext context);
}
