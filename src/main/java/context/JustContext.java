package context;

/**
 * Created by liufengkai on 2017/7/18.
 */
public interface JustContext {
    boolean contain(String name);

    Object get(String objName);
}
