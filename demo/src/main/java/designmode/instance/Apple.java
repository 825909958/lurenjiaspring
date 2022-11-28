package designmode.instance;

import java.math.BigDecimal;

/**
 * @author THT
 */
public class Apple extends Fruit {
    public Apple(int num) {
        setPrice(BigDecimal.valueOf(8));
        setNum(num);
    }


}
