package com.lfkdsk.justel.parser.fact.user;

/**
 * 维度——卖家
 *
 * @author zhulin(fengyuan)
 * @since 17/7/26.
 */
public interface Seller extends IUser {

    /**
     * 卖家ID
     *
     * @return
     */
    long getSellerId();

    /**
     * 卖家类型ID
     *
     * @return
     */
    long getSellerTypeId();

    /**
     * 判断是否是天猫卖家
     *
     * @return
     */
    default boolean isBSeller() {
        return this.hasTagOn(5, 1);
    }

    /**
     * 判断是否是淘宝卖家
     *
     * @return
     */
    default boolean isCSeller() {
        return !this.isBSeller();
    }
}
