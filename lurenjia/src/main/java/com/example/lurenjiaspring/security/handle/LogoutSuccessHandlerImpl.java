package com.example.lurenjiaspring.security.handle;


import com.alibaba.fastjson.JSON;
import com.example.lurenjiaspring.security.entity.LoginUser;
import com.example.lurenjiaspring.security.response.AjaxResult;
import com.example.lurenjiaspring.security.service.TokenService;
import com.example.lurenjiaspring.security.until.ServletUtils;
import com.example.lurenjiaspring.security.until.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 自定义退出处理类 返回成功
 *
 * @author tht
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler
{
    @Autowired
    private TokenService tokenService;

    /**
     * 退出处理
     *
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser))
        {
            String userName = loginUser.getUsername();
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getToken());
            // 记录用户退出日志
        }
        ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(200, "退出成功")));
    }
}

