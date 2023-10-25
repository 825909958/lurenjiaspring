package com.example.lurenjiaspring.config.thread;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomThreadUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{
    Logger log = LoggerFactory.getLogger(CustomThreadUncaughtExceptionHandler.class);
    @SneakyThrows
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("aaaaa");
//        throw new Exception(e.getMessage());
        log.error(t.getName() + e.getMessage());
    }
}
