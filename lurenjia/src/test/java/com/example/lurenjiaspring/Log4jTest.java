package com.example.lurenjiaspring;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LurenjiaspringApplication.class)
public class Log4jTest {
    private final Logger logger = Logger.getLogger(Log4jTest.class);

    @Test
    public void test() {
        logger.error("error");
    }
}
