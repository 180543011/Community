package com.zhiling.z.community.controller;

import com.zhiling.z.community.dto.CommentTypeEnum;
import com.zhiling.z.community.model.Comment;
import com.zhiling.z.community.service.CommentService;
import com.zhiling.z.community.service.QuestionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author zlhl
 * @Date 2019/12/27
 * @Version V1.0
 **/
@RestController
public class CommentController {

    @Resource
    private CommentService commentService;

    @Resource
    private QuestionService questionService;

    @PostMapping("/saveComment")
    public Map<String, String> saveComment(String context, Long questionId, Integer commentator){
        Map<String, String> data = new HashMap<>(2);
        Comment comment = new Comment();
        comment.setQuestionId(questionId);
        comment.setContext(context);
        comment.setParentId(questionId);
        comment.setType(CommentTypeEnum.QUESTION_COMMENT.getType());
        comment.setCommentator(commentator);
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModify(comment.getGmtCreate());
        int var = commentService.insert(comment);
        if (var > 0){
            data.put("type","success");
            questionService.incCommentCount(questionId);
        }else {
            data.put("type","error");
            data.put("message","服务器发热，凉快一下再来");
        }
        return data;
    }

}
