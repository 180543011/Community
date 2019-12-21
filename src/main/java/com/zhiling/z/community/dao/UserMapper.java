package com.zhiling.z.community.dao;

import com.zhiling.z.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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

    /**
     *  通过token获取用户信息
     * @param token token值
     * @return 用户对象
     */
    @Select("SELECT * FROM users WHERE token = #{token}")
    User getUserByToken(@Param("token") String token);
}
