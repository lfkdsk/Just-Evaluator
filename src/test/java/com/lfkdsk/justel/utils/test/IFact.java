package com.lfkdsk.justel.utils.test;

/**
 * 身份识别事实对象，是多维度的识别对象的包装实体
 * 比如OrderLineSpec:里面包含seller,buyer,item等
 *
 * @author zhulin(fengyuan)
 * @since 17/7/26.
 */
public interface IFact {

    /**
     * 将一个事实对象转换成一个标准模型
     *
     * @param <Model>
     * @return
     */
    <Model extends StandardModel> Model getStandardModel();
}
