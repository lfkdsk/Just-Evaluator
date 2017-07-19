package template.dom;

/**
 * Template File Interface
 * We just need a domCom object to
 * generateCode template file
 * Created by liufengkai on 2017/7/19.
 */
public interface Template {
    /**
     * generateCode-code template
     *
     * @return domCom-node
     */
    DomCom generateTemplate();
}
