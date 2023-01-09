package com.example.pointcutdemotest;

import com.example.lurenjiaspring.LurenjiaspringApplication;
import com.example.lurenjiaspring.aop.aspectj.PointCutDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LurenjiaspringApplication.class)
public class PointCutTest {
    @Autowired
    PointCutDemo pointCutDemo;

    @Test
    public void m() {
        pointCutDemo.b();
    }
}
