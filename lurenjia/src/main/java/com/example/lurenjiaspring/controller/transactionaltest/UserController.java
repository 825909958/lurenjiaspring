package com.example.lurenjiaspring.Controller.transactionaltest;

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
    public String A(String id) throws Exception {
        Map<String, String> map = userDomain.queryUserById(Long.parseLong(id));
        System.out.println("aaaaaaaaaaa");
        return Optional.ofNullable(map).map(Object::toString).orElse(null);
    }

    @GetMapping("/testTransactional")
    public String B(String id) throws Exception {
        Map<String, String> map = userDomain.noTransactional(Long.parseLong(id));
        System.out.println("aaaaaaaaaaa");
        return Optional.ofNullable(map).map(Object::toString).orElse(null);
    }
}
