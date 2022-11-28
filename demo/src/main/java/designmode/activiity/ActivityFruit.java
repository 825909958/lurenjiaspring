package designmode.activiity;

import designmode.instance.Fruit;

import java.math.BigDecimal;

/**
 * @author THT
 */
public interface ActivityFruit {
    /**
     * 折扣活动
     * @param fruit 实例
     * @param discount 折扣
     * @param limitTime 限制时间
     * @return BigDecimal
     */
    default void discountActive(Fruit fruit, BigDecimal discount, Long limitTime) {};

    /**
     * 满减活动
     *
     * @param fruits 实例
     * @param fullQuantity    满多少
     * @param reducedQuantity 减多少
     * @return BigDecimal
     */

    default void fullReducedQuantityActive(Fruit[] fruits, BigDecimal fullQuantity, BigDecimal reducedQuantity) {};


}
