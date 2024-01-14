package com.example.lurenjiaspring.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author THT
 */
@Data
@TableName("sys_user")
@Builder
@AllArgsConstructor
public class UserDO {
    private Long userId;
    @TableId(type = IdType.AUTO)
    private Long id;
    private String age;
    private String userName;
    private String nickName="default";
    private String password;
    private Long parentId;
    private Integer version;
    private Date createTime;

    List<UserDO> children = new ArrayList<>();


    public UserDO() {
        if (version == null) {
            version = 0;
        }
    }

    public UserDO(String userName, String nickName) {
        this.userName = userName;
        this.nickName = nickName;
    }

    public UserDO(String userName, String nickName, String password) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(password);
        this.password = encode;
        this.userName = userName;
        this.nickName = nickName;
    }

}
