package designmode.activiity;

import designmode.instance.Fruit;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author THT
 */
@Data
public class FullReduceActive extends Fruit implements ActivityFruit {
    private Fruit[] fruit;
    private BigDecimal allPrice;

    @Override
    public void fullReducedQuantityActive(Fruit[] fruits, BigDecimal fullQuantity, BigDecimal reducedQuantity) {
        if (fullQuantity.compareTo(BigDecimal.ZERO) < 0||reducedQuantity.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("参数不能为负数");
        }
        BigDecimal usuallyTotalPrice = super.getAllTotalPrice(fruits);
        allPrice = usuallyTotalPrice.compareTo(fullQuantity) >= 0 ? usuallyTotalPrice.subtract(reducedQuantity) : usuallyTotalPrice;
    }

    @Override
    public BigDecimal getAllTotalPrice(Fruit... fruits) {
        return getAllPrice();
    }

}
