package design.entity.goods.factory;


import design.constants.Constants;
import design.entity.goods.Fruit;
import design.entity.goods.Rule.Rule;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

/**
 * 水果总价和
 *
 * @author THT
 */
public class FruitFactory {
    private static List<Fruit> fruits;

    public static void setFruits(List<Fruit> fruits) {
        FruitFactory.fruits = fruits;
    }

    public static BigDecimal totalPrice() {
        if (CollectionUtils.isEmpty(fruits)) {
            return BigDecimal.ZERO;
        }
        return fruits.stream().map(FruitFactory::singleGoodSum).reduce(BigDecimal.ZERO,
                BigDecimal::add).setScale(Constants.SCALE_TWO, RoundingMode.HALF_UP);
    }

    /**
     * 对所有商品的规则之后的计算,比如所有商品满多少
     *
     * @param rule
     */
    public static BigDecimal totalPromotionalPrice(Rule rule) {
        BigDecimal totalPrice = totalPrice();
        if (totalPrice.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }
        return rule.finalAllRulesPrice(totalPrice);
    }

    /**
     * 可能存在折扣的单个商品和
     * @param fruit
     * @return
     */
    private static BigDecimal singleGoodSum(Fruit fruit) {
        BigDecimal sum = fruit.getUnitPrice().multiply(BigDecimal.valueOf(fruit.getQuantity()));
        Rule rule = fruit.getRule();
        if (Objects.isNull(rule)) {
            return sum;
        } else {
            return rule.finalAllRulesPrice(sum);
        }
    }

}
