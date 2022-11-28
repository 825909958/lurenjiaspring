package designmode.instance;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @author THT
 */

@Data
public class Fruit   {
    protected BigDecimal price;
    protected Integer num;

    public BigDecimal getTotalPrice() {
        return getPrice().multiply(BigDecimal.valueOf(num));
    }

    public  BigDecimal getAllTotalPrice(Fruit... fruits) {
        BigDecimal allTotalPrice = Arrays.stream(fruits).map(Fruit::getTotalPrice).reduce(BigDecimal.valueOf(0), BigDecimal::add);
        return allTotalPrice;
    }
}
