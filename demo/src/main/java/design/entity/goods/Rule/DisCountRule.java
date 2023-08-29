package design.entity.goods.Rule;

import design.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author THT
 */
@Data
@AllArgsConstructor
public class DisCountRule extends Rule {
    private BigDecimal inputPrice;
    private Integer disCount;

    @Override
    public BigDecimal discountRule() {
        if (disCount <= 0) {
            throw new IllegalArgumentException("N must be a positive integer.");
        }
        BigDecimal discount = BigDecimal.valueOf(getDisCount()).divide(BigDecimal.TEN, Constants.SCALE_TWO, RoundingMode.HALF_UP);
        return getInputPrice().multiply(discount).setScale(Constants.SCALE_TWO, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal finalAllRulesPrice(BigDecimal inputPrice) {
        int i = inputPrice.compareTo(BigDecimal.ZERO);
        if (i <= 0) {
            return BigDecimal.ZERO;
        }
        return discountRule();

    }
}
