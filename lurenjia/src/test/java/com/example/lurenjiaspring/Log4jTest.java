package com.example.lurenjiaspring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LurenjiaspringApplication.class)
public class Log4jTest {
    Logger logger = LoggerFactory.getLogger(Log4jTest.class);

    @Test
    public void a() {
//        Class<? extends Logger> aClass = logger.getClass();
//        String name = aClass.getName();
//        String s = String.format("name:%s; class: %s ", aClass, name);
//        System.out.println("s = " + s);
//        boolean equals = s.equals(null);
//        System.out.println("equals = " + equals);
        double ss = 11111.2172;
        String string = String.format("%.2f", ss);
        System.out.println("string = " + string);
        String a = "aa";
        String s = a + "" + ""+"ss";
        System.out.println("s = " + s);
    }
}
