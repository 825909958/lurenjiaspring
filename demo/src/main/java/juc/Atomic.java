package juc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicLong;

public class Atomic {
    static Logger logger = LoggerFactory.getLogger(Atomic.class);

    private static AtomicLong counter = new AtomicLong();

    public static void main(String[] args) {
        System.out.println(logger.getClass());
        logger.error("ss");
        // 获取当前值
        System.out.println("Current value: " + counter.get());

        // 设置新值
        counter.set(10);
        System.out.println("New value: " + counter.get());

        // 比较并设置新值
        boolean success = counter.compareAndSet(10, 20);
        System.out.println("Compare and set result: " + success);
        System.out.println("Current value: " + counter.get());

        //
        long newValue = counter.incrementAndGet();
        System.out.println("New value: " + newValue);

        // 递减
        newValue = counter.decrementAndGet();
        System.out.println("New value: " + newValue);
    }
}
