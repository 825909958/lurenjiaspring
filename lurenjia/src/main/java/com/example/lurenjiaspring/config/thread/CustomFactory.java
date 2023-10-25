package com.example.lurenjiaspring.config.thread;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class CustomFactory implements ThreadFactory {
    ThreadFactory threadFactory = Executors.defaultThreadFactory();
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = threadFactory.newThread(r);
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        thread.setName("lurenjia " + uuid);
        thread.setUncaughtExceptionHandler(new CustomThreadUncaughtExceptionHandler());
        return  thread;
    }
}
