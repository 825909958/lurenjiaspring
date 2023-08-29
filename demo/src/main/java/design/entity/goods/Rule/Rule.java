package design.entity.goods.Rule;

import java.math.BigDecimal;

/**
 * @author THT
 * 规则可针对单个商品和多个商品
 */
public class Rule {

    /**
     * 打折规则
     * @return 折扣价
     */
    public BigDecimal discountRule() {
        return BigDecimal.ZERO;
    }


    /**
     * @return 折扣价
     */
    public BigDecimal fullDiscountRule() {
        return BigDecimal.ZERO;
    }

    /**
     * 组合对个规则最终价，此类如果有多个规则则折扣多少次(新增折扣规则方法，拿到上次规则结果在调用一次新的规则)
     * @param inputPrice 输入金额
     * @return 最终价
     */
    public BigDecimal finalAllRulesPrice(BigDecimal inputPrice) {
        return BigDecimal.ZERO;
    }

}
