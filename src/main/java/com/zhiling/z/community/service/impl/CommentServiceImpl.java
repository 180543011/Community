package com.zhiling.z.community.service.impl;

import com.zhiling.z.community.dto.PageDTO;
import com.zhiling.z.community.utils.PageUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.zhiling.z.community.dao.CommentMapper;
import com.zhiling.z.community.model.Comment;
import com.zhiling.z.community.service.CommentService;

import java.util.List;

/**
 * @author zlhl
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService{

    @Resource
    private CommentMapper commentMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return commentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Comment record) {
        return commentMapper.insert(record);
    }

    @Override
    public int insertSelective(Comment record) {
        return commentMapper.insertSelective(record);
    }

    @Override
    public Comment getByPrimaryKey(Long id) {
        return commentMapper.getByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Comment record) {
        return commentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Comment record) {
        return commentMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Comment> listByUserId(Integer id, PageDTO page) {
        return commentMapper.listByUserId(id, page);
    }

    @Override
    public List<Comment> listByQuestionPage(Long questionId, Integer type, PageDTO page) {
        page.setCounts(commentMapper.countByQuestionPage(questionId,type));
        PageUtil.dealWithPage(page);
        return commentMapper.listByQuestionPage(questionId, type, page);
    }
}
