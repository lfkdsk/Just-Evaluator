package template.dom;

import context.JustContext;

import java.util.Arrays;

/**
 * Created by liufengkai on 2017/7/19.
 */
public class DescriptionComponent implements DomComponent {

    private String[] desString;

    public DescriptionComponent(String[] desString) {
        this.desString = desString;
    }

    @Override
    public StringBuilder generateCode(JustContext context, StringBuilder builder) {
        Arrays.stream(desString).map(string -> "//" + string + "\n").forEach(builder::append);
        return builder;
    }

    @Override
    public boolean isValidate(JustContext context) {
        return true;
    }
}
