package com.zhiling.z.community.service.impl;

import com.zhiling.z.community.dao.QuestionMapper;
import com.zhiling.z.community.dto.PageDTO;
import com.zhiling.z.community.model.Question;
import com.zhiling.z.community.service.QuestionService;
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
    public void insertQuestion(Question question) {
        questionMapper.insertQuestion(question);
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionMapper.getQuestionById(id);
    }

    /**
     *  返回所有question集合
     *  并对pageDTO对象属性进行处理
     * @param pageDTO 分页对象
     * @return question集合
     */
    @Override
    public List<Question> listQuestion(PageDTO pageDTO) {
        //获取分页条个数
        //获取并判断分页条数字是否符合情况
        int totalPage = pageDTO.getPageIndex() - 3;
        if (totalPage<=0){
            totalPage = 1;
        }
        int endPage = pageDTO.getPageIndex() + 3;
        if (endPage>pageDTO.getPageCount()){
            endPage = pageDTO.getPageCount();
        }
        for (int i = totalPage; i<= endPage; i++){
            pageDTO.getPages().add(i);
        }
        //当前页为第一页不显示上一页按钮
        if (pageDTO.getPageIndex()==1){
            pageDTO.setShowPrevious(false);
        }else {
            pageDTO.setShowPrevious(true);
        }
        //当前页为最后一页不显示下一页按钮
        if (pageDTO.getPageIndex().equals(pageDTO.getPageCount())){
            pageDTO.setShowNext(false);
        }else {
            pageDTO.setShowNext(true);
        }
        //是否展示第一页
        if (pageDTO.getPages().contains(1)){
            pageDTO.setShowFirstPage(false);
        }else {
            pageDTO.setShowFirstPage(true);
        }
        //是否展示最后一页
        if (pageDTO.getPages().contains(pageDTO.getPageCount())){
            pageDTO.setShowEndPage(false);
        }else {
            pageDTO.setShowEndPage(true);
        }
        return questionMapper.listQuestion(pageDTO);
    }

    @Override
    public int countQuestion() {
        return questionMapper.countQuestion();
    }

}
