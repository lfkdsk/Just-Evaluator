package com.lfkdsk.justel.parser.fact.place;


import org.junit.platform.commons.util.StringUtils;

import java.util.Map;

/**
 * 维度——请求来源
 *
 * @author zhulin(fengyuan)
 * @since 17/7/26.
 */
public interface Source extends IPlace {

    /**
     * 额外的页面参数
     *
     * @return
     */
    Map<String, String> getReqParam();

    /**
     * 页面类型
     *
     * @return
     */
    String getPageType();

    /**
     * @return 是否来自于无线形式
     */
    boolean isFromWireless();

    /**
     * @return 是否来自于PC
     */
    boolean isFromPc();

    /**
     * 是否是H5
     *
     * @return
     */
    boolean isH5Client();

    /**
     * 包含参数和值
     *
     * @param key
     * @param value
     * @return
     */
    default boolean hasParamWithValue(String key, String value) {
        if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
            return false;
        }

        if (getReqParam().isEmpty()) {
            return false;
        }

        return getReqParam().containsKey(key) && value.equals(getReqParam().get(key));
    }
}
