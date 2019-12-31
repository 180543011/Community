package com.zhiling.z.community.service.impl;

import com.zhiling.z.community.dao.QuestionMapper;
import com.zhiling.z.community.dto.PageDTO;
import com.zhiling.z.community.model.Question;
import com.zhiling.z.community.service.QuestionService;
import com.zhiling.z.community.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  Question事务接口实现
 * @Author zlhl
 * @Date 2019/12/23
 * @Version V1.0
 **/
@Service("questionService")
public class QuestionServiceImpl implements QuestionService {
    private QuestionMapper questionMapper;

    @Autowired
    public QuestionServiceImpl(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }

    @Override
    public int insertQuestion(Question question) {
        return questionMapper.insertQuestion(question);
    }

    @Override
    public int updateQuestion(Question question) {
        return questionMapper.updateQuestion(question);
    }

    @Override
    public void incViewCount(Long id) {
        questionMapper.incViewCount(id);
    }

    @Override
    public void incCommentCount(Long id) {
        questionMapper.incCommentCount(id);
    }

    @Override
    public void incLikeCount(Long id) {
        questionMapper.incLikeCount(id);
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionMapper.getQuestionById(id);
    }

    @Override
    public List<Question> listQuestionByUserId(Integer creator, PageDTO pageDTO) {
        pageDTO.setCounts(this.countQuestionByUserId(creator));
        PageUtil.dealWithPage(pageDTO);
        return questionMapper.listQuestionByUserId(creator,pageDTO);
    }

    @Override
    public int countQuestionByUserId(Integer creator) {
        return questionMapper.countQuestionByUserId(creator);
    }

    /**
     *  返回所有question集合
     *  并对pageDTO对象属性进行处理
     * @param pageDTO 分页对象
     * @return question集合
     */
    @Override
    public List<Question> listQuestion(PageDTO pageDTO) {
        pageDTO.setCounts(this.countQuestion());
        PageUtil.dealWithPage(pageDTO, 5);
        return questionMapper.listQuestion(pageDTO);
    }

    @Override
    public int countQuestion() {
        return questionMapper.countQuestion();
    }

}
