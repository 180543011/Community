package com.zhiling.z.community.controller;

import com.zhiling.z.community.model.Question;
import com.zhiling.z.community.model.User;
import com.zhiling.z.community.service.QuestionService;
import com.zhiling.z.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *  系统控制，用于跳转加工
 * @Author zlhl
 * @Date 2019/12/21
 * @Version V1.0
 **/
@Controller
public class SystemController {
    private UserService userService;
    private QuestionService questionService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @RequestMapping(path = {"/", "/index", "/index.html"})
    public String toIndex(HttpServletRequest request, Model model){
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
                        User user = userService.getUserByToken(token);
                        if (user != null){
                            //登录
                            request.getSession().setAttribute("loginUser", user);
                        }
                        break;
                    }
                }
            }
        }
        //获取问题信息
        List<Question> questions = questionService.listQuestion();
        model.addAttribute("questions", questions);

        return "index";
    }

}
