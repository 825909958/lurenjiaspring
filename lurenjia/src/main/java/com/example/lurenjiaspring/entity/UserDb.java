package com.example.lurenjiaspring.entity;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author THT
 */
@Data
public class UserDb {
    Long userId;
    Long id;
    String age;
    String userName;
    String nickName;
    String password;
    Long parentId;
    private Integer version;

    List<UserDb> children = new ArrayList<>();


    public UserDb() {
        if (version == null) {
            version = 0;
        }
    }

    public UserDb(String userName, String nickName) {
        this.userName = userName;
        this.nickName = nickName;
    }

    public UserDb(String userName, String nickName, String password) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(password);
        this.password = encode;
        this.userName = userName;
        this.nickName = nickName;
    }

}
