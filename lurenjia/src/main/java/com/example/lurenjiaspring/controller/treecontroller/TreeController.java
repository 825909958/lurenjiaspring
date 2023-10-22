package com.example.lurenjiaspring.controller.treecontroller;

import com.example.lurenjiaspring.domain.UserDomain;
import com.example.lurenjiaspring.entity.UserDb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TreeController {
    Logger logger = LoggerFactory.getLogger(TreeController.class);

    @Resource
    private UserDomain userDomain;


    @RequestMapping("/tree")
    public UserDb listUserTree() {
        logger.error("ssssssssssssssssssss");
        logger.info("是");
        System.out.println(logger.getClass());
        return userDomain.listTreeUser();//asas
    }
}
