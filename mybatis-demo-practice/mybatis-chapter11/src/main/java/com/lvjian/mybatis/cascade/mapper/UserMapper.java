package com.lvjian.mybatis.cascade.mapper;

import com.lvjian.mybatis.cascade.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    User getUserById(@Param("userId") Long userId);

    User getUserByIdFull(@Param("userId") Long userId);

    User getUserByIdForUserDetailMap(@Param("userId") Long userId);

    User getUserByIdForDiscriminator(@Param("userId") Long userId);

    User getUserByIdForJoin(@Param("userId") Long userId);

    List<User> getUsersByPhone(@Param("phone") String phone);

}
