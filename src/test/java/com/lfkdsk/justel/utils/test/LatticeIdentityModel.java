package com.lfkdsk.justel.utils.test;


import com.lfkdsk.justel.utils.test.goods.Category;
import com.lfkdsk.justel.utils.test.goods.Item;
import com.lfkdsk.justel.utils.test.place.Source;
import com.lfkdsk.justel.utils.test.user.Buyer;
import com.lfkdsk.justel.utils.test.user.Seller;

/**
 * @author zhulin(fengyuan)
 * @since 17/7/27.
 */
public class LatticeIdentityModel implements StandardModel {

    private OrderLineSpec orderLineSpec;

    public LatticeIdentityModel(OrderLineSpec orderLineSpec) {
        this.orderLineSpec = orderLineSpec;
    }

    @Override
    public Buyer getBuyer() {
        return orderLineSpec.getBuyer();
    }

    @Override
    public Seller getSeller() {
        return null;
    }

    @Override
    public Item getItem() {
        return orderLineSpec.getItem();
    }

    @Override
    public Category getCategory() {
        return null;
    }

    @Override
    public Source getSource() {
        return null;
    }
}
