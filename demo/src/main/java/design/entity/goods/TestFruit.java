package design.entity.goods;

import design.entity.goods.Rule.DisCountRule;
import design.entity.goods.Rule.FullDisCountRule;
import design.entity.goods.factory.FruitFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestFruit {
    private static final Logger logger = LoggerFactory.getLogger(TestFruit.class);
    private static final int DISCOUNT=8;
    private static final int QUANTITY_TEN=10;
    private static final int APPLE_PRICE=8;
    private static final int MANGO_PRICE=20;
    private static final int STRAWBERRY_PRICE=13;
    private static final BigDecimal REACH_AMOUNT =BigDecimal.valueOf(100);
    private static final BigDecimal DISCOUNT_AMOUNT =BigDecimal.valueOf(10);


    /**
     * 计算若干斤苹果和草莓一共花了多少钱
     */
    @Test
    public void test1() {
        List<Fruit> fruits = initAppleData();
        fruits.add(initStrawberryData());
        FruitFactory.setFruits(fruits);
        BigDecimal totalPrice = FruitFactory.totalPrice();
        logger.info("总价：{}", totalPrice);
    }

    /**
     * 计算若干斤苹果和草莓和芒果一共花了多少钱
     */
    @Test
    public void test2() {
        List<Fruit> fruits = initAppleData();
        fruits.add(initStrawberryData());
        fruits.add(initMongoData());
        FruitFactory.setFruits(fruits);
        BigDecimal totalPrice = FruitFactory.totalPrice();
        logger.info("totalPrice: {}", totalPrice);
    }

    /**
     * 计算若干斤苹果和草莓和芒果一共花了多少钱,其中草莓打8折
     */
    @Test
    public void test3() {
        Strawberry strawberry = initStrawberryData();
        strawberry.getPromotionalPriceAndInitRule(new DisCountRule(strawberry.getSingleFruitSum(), DISCOUNT));
        Mango mango = initMongoData();
        List<Fruit> fruits = initAppleData();
        fruits.add(mango);
        fruits.add(strawberry);
        FruitFactory.setFruits(fruits);
        logger.info("totalPrice:{}", FruitFactory.totalPrice());
    }

    /**
     * 计算若干斤苹果和草莓和芒果一共满了100-10的最终价
     */
    @Test
    public void test4() {
        List<Fruit> fruits = initAppleData();
        Strawberry strawberry = initStrawberryData();
        Mango mango = initMongoData();
        fruits.add(mango);
        fruits.add(strawberry);

        FruitFactory.setFruits(fruits);

        BigDecimal totalPromotionalPrice = FruitFactory.totalPromotionalPrice(new FullDisCountRule(FruitFactory.totalPrice(), REACH_AMOUNT,
                DISCOUNT_AMOUNT));
        logger.info("TotalPrice() = {}", FruitFactory.totalPrice());
        logger.info("TotalPromotionalPrice {}", totalPromotionalPrice);
    }

    private List<Fruit> initAppleData() {
        Apple apple = new Apple(BigDecimal.valueOf(APPLE_PRICE), QUANTITY_TEN);
        return Stream.of(apple)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private Strawberry initStrawberryData() {
        return new Strawberry(BigDecimal.valueOf(STRAWBERRY_PRICE), QUANTITY_TEN);
    }

    private Mango initMongoData() {
        return new Mango(BigDecimal.valueOf(MANGO_PRICE), QUANTITY_TEN);
    }

}
