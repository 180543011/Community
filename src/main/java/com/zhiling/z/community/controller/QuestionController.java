package com.zhiling.z.community.controller;

import com.zhiling.z.community.controller.exception.CustomizeErrorCodeEnum;
import com.zhiling.z.community.controller.exception.CustomizeException;
import com.zhiling.z.community.model.Question;
import com.zhiling.z.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author zlhl
 * @Date 2019/12/25
 * @Version V1.0
 **/
@Controller
public class QuestionController {
    private QuestionService questionService;

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("question.html/{id}")
    public String toQuestion(Model model, @PathVariable(name = "id") Long id){
        questionService.incViewCount(id);
        Question question = questionService.getQuestionById(id);
        if (question == null){
            throw new CustomizeException(CustomizeErrorCodeEnum.QUESTION_NOT_FOUND);
        }
        model.addAttribute("question",question);
        return "question";
    }

}
