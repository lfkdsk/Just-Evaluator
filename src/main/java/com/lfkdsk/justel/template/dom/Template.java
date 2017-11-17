package com.lfkdsk.justel.template.dom;

/**
 * Template File Interface
 * We just need a domCom object to
 * generateCode com.lfkdsk.justel.template file
 *
 * @author liufengkai
 * Created by liufengkai on 2017/7/19.
 */
@FunctionalInterface
public interface Template {
    /**
     * generateCode-code com.lfkdsk.justel.template
     *
     * @return domCom-node
     */
    DomCom generateTemplate();
}
