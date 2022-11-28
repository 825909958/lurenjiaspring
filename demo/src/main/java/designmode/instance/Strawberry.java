package designmode;

import java.math.BigDecimal;

public class Strawberry extends Fruit{
    public Strawberry(int num) {
        setPrice(BigDecimal.valueOf(13));
        setNum(num);
    }


}
