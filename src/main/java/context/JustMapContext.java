package context;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liufengkai on 2017/7/18.
 */
public class JustMapContext implements JustContext {

    private Map<String, Object> map = new HashMap<>();

    @Override
    public boolean contain(String name) {
        return map.containsKey(name);
    }

    @Override
    public Object get(String objName) {
        return map.get(objName);
    }

    @Override
    public Object put(String key, Object val) {
        return map.put(key, val);
    }
}
