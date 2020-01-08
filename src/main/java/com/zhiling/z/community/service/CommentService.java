package com.zhiling.z.community.service;

import com.zhiling.z.community.dto.PageDTO;
import com.zhiling.z.community.model.Comment;

import java.util.List;

/**
 * @author zlhl
 */
public interface CommentService{

    /**
     *  添加评论
     * @param record 评论
     * @return 添加数量
     */
    int insert(Comment record);

    /**
     *  添加评论，为空的不会写入数据库
     * @param record 评论
     * @return 添加数量
     */
    int insertSelective(Comment record);

    /**
     * 通过id删除评论
     * @param id id
     * @return 删除条数
     */
    int deleteByPrimaryKey(Long id);

    /**
     *  修改评论不修改为空
     * @param record 评论
     * @return 修改数量
     */
    int updateByPrimaryKeySelective(Comment record);

    /**
     *  全部修改评论
     * @param record 评论
     * @return 修改数量
     */
    int updateByPrimaryKey(Comment record);

    /**
     *  通过id查看评论
     * @param id id
     * @return 评论
     */
    Comment getByPrimaryKey(Long id);

    /**
     *  查询用户所有回复
     * @param id 用户id
     * @param page 分页对象
     * @return 返回该用户所有回复
     */
    List<Comment> listByUserId(Integer id, PageDTO page);

    /**
     *  查询问题下的评论
     * @param questionId 问题id
     * @param type  回复类型
     * @param page 分页对象
     * @return 所有回复
     */
    List<Comment> listByQuestionPage(Long questionId, Integer type, PageDTO page);
}
