package com.messageboard.mapper;

import com.messageboard.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    int insert(@Param("username") String username,
               @Param("email") String email,
               @Param("password") String password);

    @Select("select id, username, email, password, status from user where email = #{email} limit 1")
    User findByEmail(@Param("email") String email);

    @Select("select id, username, email, password, status from user where id = #{id} limit 1")
    User findById(@Param("id") Integer id);

    int updateStatus(@Param("id") Integer id, @Param("status") Integer status);

    int updatePasswordByEmail(@Param("email") String email, @Param("password") String password);
}
