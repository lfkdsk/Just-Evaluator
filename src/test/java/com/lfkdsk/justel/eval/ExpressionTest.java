package com.lfkdsk.justel.eval;

import com.lfkdsk.justel.JustEL;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.context.JustMapContext;
import com.lfkdsk.justel.utils.logger.Logger;
import org.junit.jupiter.api.Test;

/**
 * Created by liufengkai on 2017/9/4.
 */
class ExpressionTest {

    @Test
    void eval() {
        Logger.init();
        Logger.i(new JustEL.Builder()
                .compiler(code -> new Expression() {
                    @Override
                    public Object eval(JustContext context) {
                        return context.get("lfkdsk");
                    }
                })
                .create()
                .eval("lfkdsk", new JustMapContext() {{
                    put("lfkdsk", 1000);
                }})
                .toString());
    }
}