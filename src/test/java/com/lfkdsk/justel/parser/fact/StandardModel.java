package com.lfkdsk.justel.parser.fact;


import com.lfkdsk.justel.parser.fact.goods.Category;
import com.lfkdsk.justel.parser.fact.goods.Item;
import com.lfkdsk.justel.parser.fact.place.Source;
import com.lfkdsk.justel.parser.fact.user.Buyer;
import com.lfkdsk.justel.parser.fact.user.Seller;

import java.util.HashMap;
import java.util.Map;

/**
 * 标准身份识别模型
 *
 * @author zhulin(fengyuan)
 * @since 17/7/26.
 */
public interface StandardModel {

    /**
     * 或者买家对象
     *
     * @return
     */
    Buyer getBuyer();

    /**
     * 获取卖家对象
     *
     * @return
     */
    Seller getSeller();

    /**
     * 获取商品对象
     *
     * @return
     */
    Item getItem();

    /**
     * 获取类目对象
     *
     * @return
     */
    Category getCategory();

    /**
     * 获取渠道对象
     *
     * @return
     */
    Source getSource();

    /**
     * 构建身份解析可识别的上下文
     *
     * @return
     */
    default Map<String, Object> buildContext() {
        HashMap context = new HashMap();
        context.put("standard", this);
        context.put("item", this.getItem());
        context.put("seller", this.getSeller());
        context.put("buyer", this.getBuyer());
        context.put("category", this.getCategory());
        context.put("source", this.getSource());
        return context;
    }
}
