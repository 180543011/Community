package com.zhiling.z.community.dao;

import com.zhiling.z.community.dto.PageDTO;
import com.zhiling.z.community.model.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  问题Dao层接口
 * @Author zlhl
 * @Date 2019/12/23
 * @Version V1.0
 **/
@Mapper
@Repository
public interface QuestionMapper {

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
    Question getQuestionById(@Param("id") Long id);

    /**
     *  返回所有question集合
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
