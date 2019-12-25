package com.zhiling.z.community.controller;

import com.zhiling.z.community.dto.PageDTO;
import com.zhiling.z.community.model.Question;
import com.zhiling.z.community.model.User;
import com.zhiling.z.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *  Profile页面控制器
 * @Author zlhl
 * @Date 2019/12/24
 * @Version V1.0
 **/
@Controller
public class ProfileController {
    private QuestionService questionService;

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/profile.html/{action}")
    public String toProfile(Model model, HttpServletRequest request,
                            @PathVariable(name = "action") String action,
                            @RequestParam(value = "index", required = false, defaultValue = "1") Integer pageIndex,
                            @RequestParam(value = "size", required = false, defaultValue = "10") Integer pageSize){
        //获取session中是否有登录对象
        User loginUser = (User) request.getSession().getAttribute("loginUser");

        switch (action){
            case "reply":
                model.addAttribute("section","reply");
                model.addAttribute("sectionName","我的回答");
                break;
            case "answer":
                break;
            default:
                //创建Page对象
                PageDTO pageDTO = new PageDTO();
                pageDTO.setPageSize(pageSize);
                pageDTO.setPageIndex(pageIndex);
                List<Question> questions = questionService.listQuestionByUserId(loginUser.getId(), pageDTO);
                model.addAttribute("questions",questions);
                model.addAttribute("pageDTO", pageDTO);
                model.addAttribute("section","question");
                model.addAttribute("sectionName","我的提问");
                break;
        }
        return "profile";
    }

}
