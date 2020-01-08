package com.zhiling.z.community.controller;

import com.zhiling.z.community.controller.exception.CustomizeErrorCodeEnum;
import com.zhiling.z.community.controller.exception.CustomizeException;
import com.zhiling.z.community.dto.CommentTypeEnum;
import com.zhiling.z.community.dto.PageDTO;
import com.zhiling.z.community.model.Comment;
import com.zhiling.z.community.model.Question;
import com.zhiling.z.community.service.CommentService;
import com.zhiling.z.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @Author zlhl
 * @Date 2019/12/25
 * @Version V1.0
 **/
@Controller
public class QuestionController {
    private QuestionService questionService;
    private CommentService commentService;

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("question.html/{id}")
    public String toQuestion(Model model, @PathVariable(name = "id") Long id){
        questionService.incViewCount(id);
        Question question = questionService.getQuestionById(id);
        if (question == null){
            throw new CustomizeException(CustomizeErrorCodeEnum.QUESTION_NOT_FOUND);
        }
        PageDTO page = new PageDTO();
        page.setPageIndex(1);
        page.setPageSize(5);
        List<Comment> comments = commentService.listByQuestionPage(id, CommentTypeEnum.QUESTION_COMMENT.getType(),page);
        if (comments.size() <= 0){
            comments = null;
        }
        model.addAttribute("question",question);
        model.addAttribute("comments",comments);
        return "question";
    }

}
