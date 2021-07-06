package com.lvjian.mybatis.chapter.spring.mapper;

import com.lvjian.mybatis.chapter.spring.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Insert("insert into user(createTime, name, password, phone, nickName, gender) " +
            "values (#{user.createTime}, #{user.name}, #{user.password}, #{user.phone}, #{user.nickName}, #{user.gender})")
    int insert(@Param("user") User user);

    @Select("select * from user")
    List<User> getAllUserInfo();

}
