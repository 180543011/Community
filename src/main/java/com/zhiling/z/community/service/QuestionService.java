package com.zhiling.z.community.service;

import com.zhiling.z.community.model.Question;

import java.util.List;

/**
 * @Author zlhl
 * @Date 2019/12/23
 * @Version V1.0
 **/
public interface QuestionService {

    /**
     *  保存问题信息
     * @param question 问题对象
     */
    void insertQuestion(Question question);

    /**
     *  通过id获取问题对象
     * @param id 问题id
     * @return question对象
     */
    Question getQuestionById(Long id);

    /**
     *  返回所有question集合
     * @return question集合
     */
    List<Question> listQuestion();

}
