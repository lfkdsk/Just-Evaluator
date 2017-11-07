package com.lfkdsk.justel.utils.test.goods;

import java.util.Map;

/**
 * 维度——类目
 *
 * @author zhulin(fengyuan)
 * @since 17/7/26.
 */
public interface Category extends IGoods {

    /**
     * 获取类目ID
     *
     * @return
     */
    long getCategoryId();

    /**
     * 获取根类目ID
     *
     * @return
     */
    long getRootCategoryId();

    /**
     * 获取类目特征属性
     *
     * @return
     */
    Map<String, String> getCategoryFeatures();

    /**
     * 包含特征值
     *
     * @param featureKey
     * @param featureValue
     * @return
     */
    default boolean hasFeatureValue(String featureKey, String featureValue) {
        return true;
    }
}
