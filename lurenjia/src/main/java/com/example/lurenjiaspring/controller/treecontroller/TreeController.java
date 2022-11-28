package com.example.lurenjiaspring.controller.treecontroller;

import com.example.lurenjiaspring.domain.UserDomain;
import com.example.lurenjiaspring.entity.UserDb;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TreeController {
    @Resource
    private UserDomain userDomain;


    @RequestMapping("/tree")
    public UserDb listUserTree() {
        return userDomain.listTreeUser();
    }
}
