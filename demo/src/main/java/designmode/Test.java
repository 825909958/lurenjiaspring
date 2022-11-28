package designmode;

import designmode.activiity.Discount;
import designmode.activiity.FullReduceActive;
import designmode.instance.Apple;
import designmode.instance.Fruit;
import designmode.instance.Mango;
import designmode.instance.Strawberry;

import java.math.BigDecimal;

/**
 * @author THT
 */
public class Test {

    public static BigDecimal problem(Fruit ...fruits) {

        return  fruits[0].getAllTotalPrice(fruits);
    }

    /**
     * Strawberry 13，Apple 8，Mango 20
     * @param args
     */
    public static void main(String[] args) {
        // one
        Fruit[] fruits1 = {new Strawberry(10), new Apple(10)};
        // two
        Fruit[] fruits2 = {new Strawberry(10), new Apple(10),new Mango(10)};
        // three 26+280 限制两小时
        Discount discount = new Discount();
        discount.discountActive(new Strawberry(10),BigDecimal.valueOf(2),2L );
        Fruit[] fruits3 = {discount, new Apple(10),new Mango(10)};
        // four
        FullReduceActive fullReduceActive = new FullReduceActive();
        Fruit[] fruits4 = {new Strawberry(10), new Apple(10),new Mango(10)};
        fullReduceActive.fullReducedQuantityActive(fruits4, BigDecimal.valueOf(100), BigDecimal.valueOf(10));

        BigDecimal allTotalPrice1 = problem(fruits1);
        BigDecimal allTotalPrice2 = problem(fruits2);
        BigDecimal allTotalPrice3 = problem(fruits3);
        BigDecimal allTotalPrice4 = problem(fullReduceActive);

        System.out.println("allTotalPrice1 = " + allTotalPrice1);
        System.out.println("allTotalPrice2 = " + allTotalPrice2);
        System.out.println("allTotalPrice3 = " + allTotalPrice3);
        System.out.println("allTotalPrice4 = " + allTotalPrice4);
    }


}
