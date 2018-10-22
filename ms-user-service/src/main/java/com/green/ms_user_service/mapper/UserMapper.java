package com.green.ms_user_service.mapper;

import com.green.ms_user_service.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "userMapper")
public interface UserMapper
{
    @Select("select * from user where username = #{username}")
    List<User> getByName(@Param("username") String username);
    
    @Select("select min(`id`) from user where username = #{username}")
    Integer findIdByName(@Param("username") String username);
    
    @Insert("insert into user (id, username, password) " +
            "values (#{id}, #{username}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertUser(@Param("user") User user);
}
