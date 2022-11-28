package com.example.lurenjiaspring.security.service;

import com.example.lurenjiaspring.dao.UserDao;
import com.example.lurenjiaspring.entity.UserDb;
import com.example.lurenjiaspring.security.entity.LoginUser;
import org.apache.commons.lang3.ObjectUtils;
import org.elasticsearch.common.util.set.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@Component
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDb userDb = userDao.queryUserByUserName(username);
        if (ObjectUtils.isEmpty(userDb))
        {
            log.info("登录用户：{} 不存在.", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }

        //List<GrantedAuthority> role = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_"+"admin");
        //
        //User user = new User(username, userDb.getPassword(), role);
        LoginUser loginUser = new LoginUser();
        loginUser.setUserName(username);
        loginUser.setPassword(userDb.getPassword());
        loginUser.setPermissions(Sets.newHashSet("resource:arranged:list"));
        return loginUser;
    }
}

