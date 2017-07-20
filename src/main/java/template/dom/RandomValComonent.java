package template.dom;

import context.JustContext;
import utils.GeneratedId;

/**
 * Created by liufengkai on 2017/7/20.
 */
public class RandomValComonent implements DomComponent {
    
    @Override
    public StringBuilder generateCode(JustContext context, StringBuilder builder) {
        return builder.append("val").append(GeneratedId.generateAtomId());
    }

    @Override
    public boolean isValidate(JustContext context) {
        return true;
    }
}
