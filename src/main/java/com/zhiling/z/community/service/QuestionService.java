package com.zhiling.z.community.service;

import com.zhiling.z.community.dto.PageDTO;
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
     *  修改的问题
     * @param question 修改的问题
     */
    void updateQuestion(Question question);

    /**
     *  通过id获取问题对象
     * @param id 问题id
     * @return question对象
     */
    Question getQuestionById(Long id);

    /**
     *  查询用户发布问题
     * @param creator 创建者id
     * @param pageDTO 分页对象
     * @return 问题集合
     */
    List<Question> listQuestionByUserId(Integer creator, PageDTO pageDTO);

    /**
     *  统计用户发布的记录数
     * @param creator 创建者
     * @return 记录数
     */
    int countQuestionByUserId(Integer creator);

    /**
     *  返回所有question集合
     *  并对pageDTO对象属性进行处理
     * @param pageDTO 分页对象
     * @return question集合
     */
    List<Question> listQuestion(PageDTO pageDTO);

    /**
     *  统计总记录数
     * @return 记录数
     */
    int countQuestion();

}
