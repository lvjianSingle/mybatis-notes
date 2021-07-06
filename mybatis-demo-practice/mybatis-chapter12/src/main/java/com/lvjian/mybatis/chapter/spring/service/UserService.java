package com.lvjian.mybatis.chapter.spring.service;


import com.lvjian.mybatis.chapter.spring.entity.User;

import java.util.List;

public interface UserService {

    boolean userRegister(User user);

    List<User> getAllUserInfo();

}
