package com.zhiling.z.community.utils;

import com.zhiling.z.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *  工具类，用于使用questionService
 * @Author zlhl
 * @Date 2019/12/25
 * @Version V1.0
 **/
@Component
public class QuestionServiceUtil {
    private static QuestionService questionService;

    @Autowired
    public QuestionServiceUtil(QuestionService questionService) {
        QuestionServiceUtil.questionService = questionService;
    }

    public static QuestionService getQuestionService(){
        return questionService;
    }


}
