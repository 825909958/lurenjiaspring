package design.entity.goods.Rule;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class FullDisCountRule extends Rule {
    private BigDecimal inputPrice;
    private BigDecimal reachAmount;
    private BigDecimal discountAmount;

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
        int i = inputPrice.compareTo(BigDecimal.ZERO);
        if (i <= 0) {
            return BigDecimal.ZERO;
        }
        return fullDiscountRule();
    }
}
