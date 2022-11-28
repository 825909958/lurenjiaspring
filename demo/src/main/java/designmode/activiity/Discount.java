package designmode;

import java.math.BigDecimal;

public class Discount extends ActivityFruit {
    @Override
    public BigDecimal discountActive (BigDecimal num) {
        return getTotalPrice().multiply(num.divide(BigDecimal.valueOf(10),2));
    }
}
