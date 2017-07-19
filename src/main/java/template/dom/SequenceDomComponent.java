package template.dom;

import context.JustContext;

import java.util.Arrays;
import java.util.List;

/**
 * Sequence Component
 * We could bind a set of components in one Sequence.
 * Created by liufengkai on 2017/7/18.
 */
public class SequenceDomComponent implements DomComponent {

    private List<DomComponent> sequenceDom;

    SequenceDomComponent(DomComponent... sequenceDom) {
        this(Arrays.asList(sequenceDom));
    }

    SequenceDomComponent(List<DomComponent> sequenceDom) {
        this.sequenceDom = sequenceDom;
    }

    @Override
    public StringBuilder generateCode(JustContext context, StringBuilder builder) {
        sequenceDom.forEach(domComponent -> domComponent.generateCode(context, builder));
        return builder;
    }

    @Override
    public boolean isValidate(JustContext context) {
        return sequenceDom.stream().allMatch((DomComponent con) -> con.isValidate(context));
    }
}
