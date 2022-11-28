package com.example.lurenjiaspring.controller.countpeople;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

@Controller
public class IndexController
        //implements ApplicationContextAware
{


    //@Override
    //public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    //    PermissionService ss = (PermissionService) applicationContext.getBean("ss");
    //    boolean b = ss.hasPermi("resource:arranged:list");
    //    System.out.println(b);
    //}

// 一个@符号漏了搞半天
    @PostMapping("/count")
    @ResponseBody
    @PreAuthorize("@ss.hasPermi('resource:arranged:list')")
//    @PreAuthorize("hasAnyRole('role')")
//    @PreAuthorize("hasAnyRole('admin')")
    public String number(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {  //把sessionId记录在浏览器
            Cookie c = new Cookie("JSESSIONID", URLEncoder.encode(httpServletRequest.getSession().getId(), "utf-8"));
            c.setPath("/");
            //先设置cookie有效期为2天，不用担心，session不会保存2天
            c.setMaxAge(48 * 60 * 60);
            httpServletResponse.addCookie(c);
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpSession session = httpServletRequest.getSession();
        Object count = session.getServletContext().getAttribute("count");
        return "count : " + count;
    }

    @RequestMapping("/cacel")
    @ResponseBody
    public String cacelNumber(HttpSession httpSession) {
        httpSession.invalidate();
        return "count : " + httpSession.getServletContext().getAttribute("count");
    }


}
