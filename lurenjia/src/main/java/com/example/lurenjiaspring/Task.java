package com.example.lurenjiaspring;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.example.lurenjiaspring.LurenjiaspringApplication.takeData;

public class Task implements Runnable{

    private static final Logger logger = LoggerFactory.getLogger(LurenjiaspringApplication.class);

    @Override
    public void run() {
        for (; ; ) {
            try {
                Integer data = takeData();
                if (ObjectUtils.isNotEmpty(data)) {
                    logger.info(data.toString());
                }
            } catch (Exception e) {
                logger.error("获取队列数据失败");
            }
        }
    }
}
