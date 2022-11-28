package designmode;

import java.math.BigDecimal;

public class Apple extends Fruit{
    public Apple(int num) {
        setPrice(BigDecimal.valueOf(8));
        setNum(num);
    }


}
