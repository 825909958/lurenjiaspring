package com.example.lurenjiaspring.controller.transactionaltest;

import com.example.lurenjiaspring.domain.UserDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

/**
 * @author THT
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDomain userDomain;

    @GetMapping("/get")
    public Optional A(String id) throws Exception {
        Map<String, Object> map = userDomain.queryUserById(Long.parseLong(id));
        return Optional.ofNullable(map).map(Object::toString);
    }

    @GetMapping("/testTransactional")
    public String B(String id) throws Exception {
        Map<String, Object> map = userDomain.noTransactional(Long.parseLong(id));
        System.out.println("aaaaaaaaaaa");
        return Optional.ofNullable(map).map(Object::toString).orElse(null);
    }
}
