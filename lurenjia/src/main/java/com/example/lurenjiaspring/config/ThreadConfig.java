package com.example.lurenjiaspring.config;

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
    @Bean
    /**
     * 4核8线程，io密集型推荐参数
     */
    public ThreadPoolExecutor threadPoolExecutor() {
        return new ThreadPoolExecutor(8, 16, 1L, TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(100), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());

    }

    /**
     * 4核8线程，cpu密集型推荐参数
     * @return
     */
    public ThreadPoolExecutor threadPoolExecutor1() {
        return new ThreadPoolExecutor(4, 4, 1L, TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(100), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());

    }
}
