package com.lfkdsk.justel.utils.test;

import com.lfkdsk.justel.JustEL;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.context.JustMapContext;
import com.lfkdsk.justel.eval.Expression;
import com.lfkdsk.justel.utils.logger.Logger;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class OrderTest {
    @Test
    void testOrder() {
        IFact iFact = new OrderLineSpec();
        Map<String, Object> map = iFact.getStandardModel().buildContext();

        JustContext context1 = new JustMapContext();

//        java.lang.reflect.Method method = ReflectUtils.getMethod(LatticeIdentityModel.class,"getItem",new Class[]{});

        for (Map.Entry<String, Object> stringObjectEntry : map.entrySet()) {
            if (stringObjectEntry.getKey().equals("standard"))
                context1.put(stringObjectEntry.getKey(), stringObjectEntry.getValue());
        }

        Logger.init();
//        Logger.v(JustEL.runCompile("!item.hasFeature(\"fengyuan\") "
//                .replace("item.","standard.item.")
//                , context).eval(context).toString());

        Expression expr = JustEL.runCompile("!item.hasFeature(\"fengyuan\") && !item.hasFeature(\"fengyuan\")"
                .replace("item.", "standard.item."), context1);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1_0000_0000; i++) {
            expr.eval(context1);
        }

        System.out.println(System.currentTimeMillis() - start);
    }
}
