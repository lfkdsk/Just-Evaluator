package com.lfkdsk.justel.template.dom;

import com.lfkdsk.justel.context.JustContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Dom fixed structure Component Binder.
 * We bind structure use static method in this class.
 * Usage:
 * <code>
 * private static final DomCom classGen = DomCom.rule()
 * .sep("public class")
 * .bind("${className}")
 * .sep("implement Expression")
 * .sep("{")
 * .append(functionGen)
 * .sep("}");
 * </code>
 *
 * @see com.lfkdsk.justel.template.TemplateImpl to get more usages
 * Created by liufengkai on 2017/7/18.
 */
public class DomCom {

    /**
     * a set of components
     */
    private List<DomComponent> components;

    private DomCom() {
        this.components = new ArrayList<>();
    }

    public static DomCom rule() {
        return new DomCom();
    }

    /**
     * return all components
     *
     * @return list of subDom
     */
    private List<DomComponent> getComponents() {
        return components;
    }

    /**
     * Add Ignore-Component
     * Ignore-Component will not check exchange Vars
     *
     * @param comString a set of ignore-string
     * @return bind ignore DomCom
     */
    public DomCom sep(String... comString) {
        components.add(new IgnoreDomComponent(comString));
        return this;
    }

    /**
     * Bind Single-Vars Dom Component In Components.
     *
     * @param bindVar vars-exchange-code
     * @return bind vars-DomCom
     */
    public DomCom bind(String bindVar) {
        components.add(new SingleDomComponent(bindVar));
        return this;
    }

    /**
     * Bind one Component to DomCom
     *
     * @param component new component
     * @return binder-DomCom
     */
    public DomCom ast(DomComponent component) {
        components.add(component);
        return this;
    }

    /**
     * Bind a sequence of Components at the last of DomCom
     *
     * @param component a set of components
     * @return return binder-DomCom
     */
    public DomCom seq(DomComponent... component) {
        components.add(new SequenceDomComponent(component));
        return this;
    }

    /**
     * Append a DomCom to self
     *
     * @param domCom new-domCom
     * @return return binder-DomCom
     */
    public DomCom append(DomCom domCom) {
        components.addAll(domCom.getComponents());
        return this;
    }

    public DomCom nop(String... nopStrings) {
        return this;
    }

    public DomCom des(String... desStrings) {
        components.add(new DescriptionComponent(desStrings));
        return this;
    }

    public DomCom randomVal() {
        components.add(new RandomValComponent());
        return this;
    }

    /**
     * Return Fake Generate Component.
     *
     * @return return a set of sequence dom.
     */
    public DomComponent fakeGenerateComponent() {
        return new SequenceDomComponent(components);
    }

    /**
     * Return Fake Generate String.
     *
     * @param context Vars Context.
     * @return return fake generateCode string.
     */
    public String fakeGenerateString(JustContext context) {
        return fakeGenerateComponent()
                .generateCode(context, new StringBuilder())
                .toString();
    }
}
