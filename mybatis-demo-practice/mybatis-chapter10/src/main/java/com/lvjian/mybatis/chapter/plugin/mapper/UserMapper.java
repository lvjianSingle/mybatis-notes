package com.lvjian.mybatis.chapter.plugin.mapper;

import com.lvjian.mybatis.chapter.plugin.entity.UserEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Select("select * from user")
    List<UserEntity> getUserPageable();

}
