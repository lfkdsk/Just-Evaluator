package template.dom;

import context.JustContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liufengkai on 2017/7/18.
 */
public class DomCom {
    public List<DomComponent> components;

    public DomCom() {
        this.components = new ArrayList<>();
    }

    public List<DomComponent> getComponents() {
        return components;
    }

    public DomCom sep(String... comString) {
        components.add(new IgnoreDomComponent(comString));
        return this;
    }

    public DomCom ast(DomComponent component) {
        components.add(component);
        return this;
    }

    public DomCom bind(DomComponent... component) {
        components.add(new SequenceDomComponent(component));
        return this;
    }

    public DomCom append(DomCom domCom) {
        components.addAll(domCom.getComponents());
        return this;
    }

    public DomComponent fakeGenerateComponent() {
        return new SequenceDomComponent(components);
    }

    public String fakeGenerateString(JustContext context) {
        return fakeGenerateComponent()
                .generate(context, new StringBuilder())
                .toString();
    }
}
