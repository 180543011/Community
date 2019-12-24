package com.zhiling.z.community.service.impl;

import com.zhiling.z.community.dao.QuestionMapper;
import com.zhiling.z.community.model.Question;
import com.zhiling.z.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
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
    public void insertQuestion(Question question) {
        questionMapper.insertQuestion(question);
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionMapper.getQuestionById(id);
    }

    @Override
    public List<Question> listQuestion() {
        return questionMapper.listQuestion();
    }
}
