package com.zhiling.z.community.dao;

import com.zhiling.z.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author zlhl
 * @Date 2019/12/21
 * @Version V1.0
 **/
@Mapper
@Repository
public interface UserMapper {

    /**
     *  创建用户
     * @param user 用户
     */
    @Insert("INSERT INTO users(name, accountId, token, gmtCreate, gmtModify) VALUES(#{name}, " +
            "#{accountId}, #{token}, #{gmtCreate}, #{gmtModify})")
    void insertUser(User user);

}