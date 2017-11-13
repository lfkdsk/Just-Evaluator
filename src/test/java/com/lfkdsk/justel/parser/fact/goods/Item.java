package com.lfkdsk.justel.parser.fact.goods;

import com.lfkdsk.justel.template.StringUtils;

import java.util.Map;
import java.util.Set;

/**
 * 维度——商品
 *
 * @author zhulin(fengyuan)
 * @since 17/7/26.
 */
public interface Item extends IGoods {

    /**
     * 商品标签
     *
     * @return
     */
    Set<Integer> getItemTags();

    /**
     * 商品选项
     *
     * @return
     */
    Long getItemOptions();

    /**
     * 获取商品特征
     *
     * @return
     */
    Map<String, String> getItemFeatures();

    /**
     * 获取商品类型
     *
     * @return
     */
    String getAuctionType();

    /**
     * 获取商品ID
     *
     * @return
     */
    Long getItemId();

    /**
     * 商品新旧程度
     * 5表示全新，6表示二手，8表示闲置
     *
     * @return
     */
    Integer getStuffStatus();

    /**
     * 判断商品类型是否符合
     *
     * @param auctionType
     * @return
     */
    default boolean matchAuctionType(String auctionType) {
        return StringUtils.equals(auctionType, auctionType);
    }

    /**
     * 判断是否包含商品标
     *
     * @param tag
     * @return
     */
    default boolean hasTag(int tag) {
        if (getItemTags().isEmpty()) {
            return false;
        }
        return getItemTags().contains(tag);
    }

    /**
     * 判断是否包含商品Option
     *
     * @param option
     * @return
     */
    default boolean hasOption(long option) {
        if (getItemOptions() == null) {
            return false;
        }
        return (getItemOptions() & option) == option;
    }

    /**
     * 包含商品特征
     *
     * @param key
     * @return
     */
    default boolean hasFeature(String key) {
        if (getItemFeatures().isEmpty() || StringUtils.isEmpty(key)) {
            return false;
        }
        return getItemFeatures().containsKey(key) && StringUtils.isNotEmpty(getItemFeatures().get(key));
    }
}
