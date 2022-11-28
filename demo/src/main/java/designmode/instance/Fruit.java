package designmode;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author THT
 */

@Data
public  class Fruit {
    protected BigDecimal price;
    protected Integer num;

    public BigDecimal getTotalPrice() {
        return getPrice().multiply(BigDecimal.valueOf(num));
    }

}
