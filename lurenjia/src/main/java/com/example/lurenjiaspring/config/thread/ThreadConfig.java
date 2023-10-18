package com.example.lurenjiaspring.config.thread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author THT
 */
@Configuration
public class ThreadConfig {
    static int numCores = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        System.out.println("numCores = " + numCores);
    }

    //极端情况下才会使用到最大线程数，正常情况下不应频繁出现超过核心线程数的创建。
    @Bean
    /**
     * 4核8线程，io密集型推荐参数
     */
    public ThreadPoolExecutor threadPoolExecutor() {
        return new ThreadPoolExecutor(8, 17, 1L, TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(100), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());

    }

    /**
     * 4核8线程，cpu密集型推荐参数
     * @return
     */
    public ThreadPoolExecutor threadPoolExecutor1() {
        return new ThreadPoolExecutor(4, 5, 1L, TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(100), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());

    }


}
