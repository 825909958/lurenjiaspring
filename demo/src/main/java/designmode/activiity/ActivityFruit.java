package designmode;

import designmode.instance.Fruit;

import java.math.BigDecimal;

public  class ActivityFruit extends Fruit {
    /**
     *折扣活动
     *
     * @param discount  折扣
     * @return
     */
    public  BigDecimal discountActive(BigDecimal discount){
        return BigDecimal.valueOf(0);
    };

    /**
     * 满减活动
     *
     * @param fullQuantity 满多少
     * @param reducedQuantity 减多少
     * @return
     */

    public  BigDecimal fullReducedQuantityActive(BigDecimal fullQuantity,BigDecimal reducedQuantity) {
        return BigDecimal.valueOf(0);
    }


}
