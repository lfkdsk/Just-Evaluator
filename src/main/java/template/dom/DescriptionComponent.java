package template.dom;

import context.JustContext;

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
//        Arrays.stream(desString).map(string -> "//" + string + "\n").forEach(builder::append);
        for (String des : desString) {
            builder.append("//").append(des).append("\n");
        }
        return builder;
    }

    @Override
    public boolean isValidate(JustContext context) {
        return true;
    }
}
