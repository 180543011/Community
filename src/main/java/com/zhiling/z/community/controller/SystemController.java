package com.zhiling.z.community.controller;

import com.zhiling.z.community.dto.PageDTO;
import com.zhiling.z.community.model.Question;
import com.zhiling.z.community.model.User;
import com.zhiling.z.community.service.QuestionService;
import com.zhiling.z.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    /**
     *  主页
     */
    @RequestMapping(path = {"/", "/index", "/index.html"})
    public String toIndex(HttpServletRequest request, Model model,
                          @RequestParam(value = "index", required = false, defaultValue = "1") Integer pageIndex,
                          @RequestParam(value = "size", required = false, defaultValue = "1") Integer pageSize){
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
        //创建Page对象
        PageDTO pageDTO = new PageDTO();
        //判断页面行数是否超标
        if (pageSize<=0){
            pageSize = 10;
        }
        pageDTO.setPageSize(pageSize);
        pageDTO.setCounts(questionService.countQuestion());
        pageDTO.setPageCount((pageDTO.getCounts() -1)/ pageDTO.getPageSize()+1);
        //判断index是否超标
        if (pageIndex<=0){
            pageIndex = 1;
        }
        if(pageIndex>pageDTO.getPageCount()){
            pageIndex = pageDTO.getPageCount();
        }
        pageDTO.setPageIndex(pageIndex);
        //获取问题信息
        List<Question> questions = questionService.listQuestion(pageDTO);
        model.addAttribute("questions", questions);
        model.addAttribute("pageDTO", pageDTO);
        return "index";
    }

}
