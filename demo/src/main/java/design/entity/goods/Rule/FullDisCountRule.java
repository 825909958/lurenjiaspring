package design.entity.goods.Rule;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FullDisCountRule extends Rule {
    private BigDecimal inputPrice;
    private BigDecimal reachAmount;
    private BigDecimal discountAmount;

    public FullDisCountRule(BigDecimal reachAmount, BigDecimal discountAmount) {
        this.reachAmount = reachAmount;
        this.discountAmount = discountAmount;
    }

    @Override
    public BigDecimal fullDiscountRule() {
        if (inputPrice.compareTo(reachAmount) >= 0) {
            return inputPrice.subtract(discountAmount);
        } else {
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal finalAllRulesPrice(BigDecimal inputPrice) {
        this.inputPrice = inputPrice;
        int i = inputPrice.compareTo(BigDecimal.ZERO);
        if (i <= 0) {
            return BigDecimal.ZERO;
        }
        return fullDiscountRule();
    }
}
