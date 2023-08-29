package com.example.lurenjiaspring.security.controller;

import com.example.lurenjiaspring.security.entity.LoginBody;
import com.example.lurenjiaspring.security.entity.LoginUser;
import com.example.lurenjiaspring.security.exception.CustomException;
import com.example.lurenjiaspring.security.exception.user.UserPasswordNotMatchException;
import com.example.lurenjiaspring.security.response.AjaxResult;
import com.example.lurenjiaspring.security.service.TokenService;
import com.example.lurenjiaspring.security.until.Constants;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class UserContoller {
    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private TokenService tokenService;

    @PostMapping("/login")
    public AjaxResult loginForword(
            @RequestBody
            LoginBody loginBody, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        AjaxResult ajax = AjaxResult.success();
        //if (true) {
        //    throw new DemoModeException();
        //}
        request.getRequestURI();
        String token = login(loginBody.getUsername(), loginBody.getPassword(), request, response);
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }


    public String login(String username, String password, HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {

// 用户验证
        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
//            authentication = authenticationManager
//                    .authenticate(new UsernamePasswordAuthenticationToken(loginBody.getUsername(), loginBody.getPassword()));
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {

                throw new UserPasswordNotMatchException();
            } else {
                throw new CustomException(e.getMessage());
            }
        }
        //LoginUser loginUser = new LoginUser();
        //loginUser.setUserName(username);
        //loginUser.setPassword(password);
        //List<GrantedAuthority> role = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_" + "admin");
        //
        //// 生成token
        //UsernamePasswordAuthenticationToken authenticationToken =       /*loginUser.getAuthorities()*/
        //        new UsernamePasswordAuthenticationToken(loginUser, null, role);
        //authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        //SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //response.sendRedirect("http://localhost:8080/count");


        // 1.请求的转发：地址栏是初次发出请求的地址。
        //
        //     请求的重定向：地址栏不是初次发出请求的地址，为最后响应的地址。
        //
        //  2.请求转发：在最终的Servlet中，request和中转的那个request是同一个对象。
        //
        //     请求的重定向：在最终的Servlet中，request和中转的那个request不是同一个对象。
        //
        //  3.请求转发：只能转发给当前web应用的资源。
        //
        //    请求的重定向：可以重定向到任何资源。
        //
        //  4.请求的转发：/代表的是当前web应用的根目录。
        //
        //    请求的重定向：/代表的是当前web站点的根目录。
        //RequestDispatcher requestDispatcher=request.getRequestDispatcher("count");
        //
        //requestDispatcher.forward(request,response);

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 生成token
        return tokenService.createToken(loginUser);
    }
}
