package com.lvjian.mybaits.chapter.mapper;

import com.lvjian.mybaits.chapter.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Title: UserMapper
 * Description: UserMapper
 *
 * @author lvjian
 * @version 1.0.0
 * @date 2021/6/23 20:43
 */
public interface UserMapper {

    List<UserEntity> listAllUser();

    @Select("select * from user where id=#{userId,jdbcType=INTEGER}")
    UserEntity getUserById(@Param("userId") String userId);

    List<UserEntity> getUserByEntity( UserEntity user);

    UserEntity getUserByPhone(@Param("phone") String phone);

}
