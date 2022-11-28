package designmode.activiity;

import designmode.instance.Fruit;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class FullReduceActivi extends Fruit implements ActivityFruit {
    private Fruit[] fruit;
    private BigDecimal allPrice;

    @Override
    public void fullReducedQuantityActive(Fruit[] fruits, BigDecimal fullQuantity, BigDecimal reducedQuantity) {
        BigDecimal usuallyTotalPrice = super.getAllTotalPrice(fruits);
        BigDecimal allTotalPrice = usuallyTotalPrice.compareTo(fullQuantity) >= 0 ? usuallyTotalPrice.subtract(reducedQuantity) : usuallyTotalPrice;
        allPrice = allTotalPrice;
    }

    @Override
    public BigDecimal getAllTotalPrice(Fruit... fruits) {
        return getAllPrice();
    }

}
