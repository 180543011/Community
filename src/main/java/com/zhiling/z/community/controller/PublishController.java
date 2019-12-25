package com.zhiling.z.community.controller;

import com.zhiling.z.community.model.Question;
import com.zhiling.z.community.model.User;
import com.zhiling.z.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

/**
 *  publish页面控制层
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

    /**
     *  publish get请求
     */
    @GetMapping("/publish.html/{id}")
    public String toPublish(Model model, @PathVariable(name = "id", required = false) Long id){
        if (id != null && id > 0){
            Question question = questionService.getQuestionById(id);
            model.addAttribute("id",id);
            model.addAttribute("title", question.getTitle());
            model.addAttribute("description",question.getDescription());
            model.addAttribute("tag", question.getTag());
        }
        return "publish";
    }

    /**
     * post请求 保存问题发布
     */
    @PostMapping("/publish.html/0")
    public String doPublish(Question question, HttpServletRequest request, Model model){
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        //判断是否接收表单数据
        if (question.getTitle() != null && !"".equals(question.getTitle()) &&
                question.getDescription() != null && !"".equals(question.getDescription())){
            //判断是否登录用户，可以判断是否启用匿名用户
            if (loginUser != null){
                if (question.getId() != null && question.getId() != 0){
                    question.setGmtModify(System.currentTimeMillis());
                    questionService.updateQuestion(question);
                    return "redirect:/question.html/"+question.getId();
                }else {
                    question.setCreator(loginUser.getId());
                    question.setGmtCreate(System.currentTimeMillis());
                    question.setGmtModify(question.getGmtCreate());
                    questionService.insertQuestion(question);
                    return "redirect:/index";
                }
            }else {
                request.setAttribute("massage","用户未登录或登录已经过期");
            }
        }else {
            model.addAttribute("massage","标题，内容不能为空！");
        }
        model.addAttribute("id",question.getId());
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag", question.getTag());
        return "publish";
    }

}
