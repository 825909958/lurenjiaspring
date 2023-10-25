package com.example.lurenjiaspring.controller.treecontroller;

import com.example.lurenjiaspring.domain.UserDomain;
import com.example.lurenjiaspring.entity.UserDb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ThreadPoolExecutor;

@RestController
public class TreeController {
    Logger logger = LoggerFactory.getLogger(TreeController.class);

    @Resource
    private UserDomain userDomain;

    @Autowired
    private ThreadPoolExecutor threadPoolExecutorException;


    @RequestMapping("/tree")
    public UserDb listUserTree() {
        String tid = MDC.get("tid");
//        logger.error("ssssssssssssssssssss");
//        System.out.println(logger.getClass());
        threadPoolExecutorException.execute(() -> {
            MDC.put("tid", tid);
            logger.error("xxxxxx");
            int i = 1 / 0;
        });

        return userDomain.listTreeUser();//asas
    }
}
