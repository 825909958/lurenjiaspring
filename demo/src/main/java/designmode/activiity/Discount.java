package designmode.activiity;

import designmode.common.Constants;
import designmode.instance.Fruit;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author THT
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Component
public class Discount extends Fruit implements ActivityFruit {
    private Fruit fruit;
    private BigDecimal discountPrice;
    private Long limitTime;
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    private static Boolean flag = true;

    @Override
    public void discountActive(Fruit fruit, BigDecimal discount, Long limitTime) {
        this.fruit = fruit;

        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        String value = opsForValue.get(Constants.EXPIRE_TIME_KEY);
        if (flag&& value ==null) {
            opsForValue.set(Constants.EXPIRE_TIME_KEY, "1", limitTime, TimeUnit.HOURS);
            flag = false;
        }
        if (value != null) {
            this.discountPrice = fruit.getTotalPrice().multiply(discount.divide(BigDecimal.valueOf(10), 2, 2));
        } else {
            this.discountPrice = fruit.getTotalPrice();
        }

    }

    @Override
    public BigDecimal getTotalPrice() {
        return discountPrice;
    }
}
