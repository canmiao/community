package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


/**
 * @Classname UserMapper
 * @Description TODO
 * @Date 2019/6/9 17:04
 * @Created by hp
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})" )
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findBytoken(@Param("token") String token);
}
