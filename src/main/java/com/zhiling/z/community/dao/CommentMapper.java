package com.zhiling.z.community.dao;

import com.zhiling.z.community.dto.PageDTO;
import com.zhiling.z.community.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.PageFormat;
import java.util.List;

/**
 * @author zlhl
 */
@Mapper
@Repository
public interface CommentMapper {

    /**
     * 添加评论
     *
     * @param record 评论
     * @return 添加数量
     */
    int insert(Comment record);

    /**
     * 添加评论，为空的不会写入数据库
     *
     * @param record 评论
     * @return 添加数量
     */
    int insertSelective(Comment record);

    /**
     * 通过id删除评论
     *
     * @param id id
     * @return 删除条数
     */
    int deleteByPrimaryKey(@Param("id") Long id);

    /**
     * 修改评论不修改为空
     *
     * @param record 评论
     * @return 修改数量
     */
    int updateByPrimaryKeySelective(Comment record);

    /**
     * 全部修改评论
     *
     * @param record 评论
     * @return 修改数量
     */
    int updateByPrimaryKey(Comment record);

    /**
     * 通过id查看评论
     *
     * @param id id
     * @return 评论
     */
    Comment getByPrimaryKey(@Param("id") Long id);

    /**
     *  查询问题下的评论
     * @param questionId 问题id
     * @param type  回复类型
     * @param page 分页对象
     * @return 所有回复
     */
    List<Comment> listByQuestionPage(@Param("questionId") Long questionId, @Param("type") Integer type,
                                     @Param("page") PageDTO page);

    /**
     *  统计问题下的评论数
     * @param questionId 问题id
     * @param type  回复类型
     * @return 所有回复数
     */
    int countByQuestionPage(@Param("questionId") Long questionId, @Param("type") Integer type);

    /**
     * 查询用户所有回复
     *
     * @param id   用户id
     * @param page 分页对象
     * @return 返回该用户所有回复
     */
    List<Comment> listByUserId(@Param("id") Integer id, @Param("page") PageDTO page);

}