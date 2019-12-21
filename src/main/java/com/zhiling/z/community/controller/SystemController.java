package com.zhiling.z.community.controller;

import com.zhiling.z.community.model.User;
import com.zhiling.z.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *  系统控制，用于跳转加工
 * @Author zlhl
 * @Date 2019/12/21
 * @Version V1.0
 **/
@Controller
public class SystemController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = {"/", "/index", "/index.html"})
    public String toIndex(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("communityToken".equals(cookie.getName())){
                String token = cookie.getValue();
                User user = userService.getUserByToken(token);
                if (user != null){
                    request.getSession().setAttribute("loginUser", user);
                }
                break;
            }
        }
        return "index";
    }

}
