package com.zhiling.z.community.controller;

import com.zhiling.z.community.model.Question;
import com.zhiling.z.community.model.User;
import com.zhiling.z.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author zlhl
 * @Date 2019/12/23
 * @Version V1.0
 **/
@Controller
public class PublishController {

    private QuestionService questionService;

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/publish.html")
    public String toPublish(){
        return "publish";
    }

    @PostMapping("/publish.html")
    public String doPublish(Question question, HttpServletRequest request, Model model){
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        //判断是否接收表单数据
        if (question.getTag() != null && question.getTitle() != null && question.getDescription() != null){
            //判断是否登录用户，可以判断是否启用匿名用户
            if (loginUser != null){
                question.setCreator(loginUser.getId());
                question.setGmtCreate(System.currentTimeMillis());
                question.setGmtModify(question.getGmtCreate());
                questionService.insertQuestion(question);
                return "redirect:/index";
            }else {
                request.setAttribute("massage","用户未登录或登录已经过期");
            }
        }else {
            model.addAttribute("massage","标题，内容，标签均不能为空！");
        }
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        return "publish";
    }

}
