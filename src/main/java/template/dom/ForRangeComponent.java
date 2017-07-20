package template.dom;

import context.JustContext;

import java.util.Objects;

/**
 * For-RangeComponent. It's just an option
 * to be extended to an template engine.
 * Created by liufengkai on 2017/7/20.
 */
public class ForRangeComponent implements DomComponent {

    private int startVal = 0, endVal = 0;
    private String block;

    public ForRangeComponent(int startVal, int endVal, String block) {
        this.startVal = startVal;
        this.endVal = endVal;
        this.block = block;
    }

    @Override
    public StringBuilder generateCode(JustContext context, StringBuilder builder) {
        // create local val
        String localVal = "val" + Objects.hashCode(System.currentTimeMillis());
        return builder.append("for(int ")
                .append(localVal)
                .append("=")
                .append(startVal)
                .append(localVal)
                .append(";")
                .append("<")
                .append(endVal)
                .append(";")
                .append(localVal)
                .append("++) {")
                .append(block)
                .append("}");
    }

    @Override
    public boolean isValidate(JustContext context) {
        return context.contain(block);
    }
}
