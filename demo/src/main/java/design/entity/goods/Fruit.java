package design.entity.goods;

import design.constants.Constants;
import design.entity.goods.Rule.Rule;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author THT
 */
@Data
public class Fruit {
    /**
     * 单价
     */
    private BigDecimal unitPrice;
    /**
     * 数量
     */
    private Integer quantity;

    private Rule rule;

    public Fruit(BigDecimal unitPrice, Integer quantity) {
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    /**
     * 可传入多个规则，得到最终促销价
     *
     * @param rule 规则
     * @return 促销价
     */
    public BigDecimal getPromotionalPriceAndInitRule(Rule rule) {
        this.rule = rule;
        int i = unitPrice.compareTo(BigDecimal.ZERO);
        if (i <= 0) {
            return BigDecimal.ZERO;
        }
        return rule.finalAllRulesPrice(getUnitPrice());
    }

    /**
     * 得到单个商品为打折和
     * @return
     */
    public BigDecimal getSingleFruitSum() {
        if (unitPrice.compareTo(BigDecimal.ZERO) <= 0 || quantity <= 0) {
            throw new RuntimeException("invalid range");
        }
        return unitPrice.multiply(BigDecimal.valueOf(quantity)).setScale(Constants.SCALE_TWO, RoundingMode.HALF_UP);
    }

    public Rule getRule() {
        return rule;
    }
}
