package designmode.instance;

import java.math.BigDecimal;

/**
 * @author THT
 */
public class Mango extends Fruit {
    public Mango(int num) {
        setPrice(BigDecimal.valueOf(20));
        setNum(num);
    }

}
