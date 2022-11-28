package designmode.instance;

import java.math.BigDecimal;

/**
 * @author THT
 */
public class Strawberry extends Fruit  {
    public Strawberry(int num) {
        setPrice(BigDecimal.valueOf(13));
        setNum(num);
    }


}
