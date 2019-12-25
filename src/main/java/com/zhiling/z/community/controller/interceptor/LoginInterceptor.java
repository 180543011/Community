package com.zhiling.z.community.controller.interceptor;

import com.zhiling.z.community.model.User;
import com.zhiling.z.community.utils.UserServiceUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author zlhl
 * @Date 2019/12/25
 * @Version V1.0
 **/
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取session中是否有登录对象
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        //判断是否登录成功
        if (loginUser == null){
            //查看是否有cookie信息
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("communityToken".equals(cookie.getName())){
                        String token = cookie.getValue();
                        User user = UserServiceUtil.getUserService().getUserByToken(token);
                        if (user != null){
                            //登录
                            request.getSession().setAttribute("loginUser", user);
                        }
                        break;
                    }
                }
            }
        }
        return true;
    }
}
