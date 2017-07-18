package template.dom;

import context.JustContext;

import java.util.Arrays;
import java.util.List;

/**
 * Created by liufengkai on 2017/7/18.
 */
public class SequenceDomComponent implements DomComponent {

    private List<DomComponent> sequenceDom;

    public SequenceDomComponent(DomComponent... sequenceDom) {
        this(Arrays.asList(sequenceDom));
    }

    public SequenceDomComponent(List<DomComponent> sequenceDom) {
        this.sequenceDom = sequenceDom;
    }

    @Override
    public StringBuilder generate(JustContext context, StringBuilder builder) {
        for (DomComponent domComponent : sequenceDom) {
            domComponent.generate(context, builder);
        }
        return builder;
    }

    @Override
    public boolean validation(JustContext context) {
        return sequenceDom.stream()
                .filter((DomComponent con) -> !con.validation(context))
                .count() > 0;
    }
}
