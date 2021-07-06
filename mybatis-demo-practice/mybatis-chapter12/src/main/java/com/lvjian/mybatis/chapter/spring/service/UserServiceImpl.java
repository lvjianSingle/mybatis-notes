package com.lvjian.mybatis.chapter.spring.service;

import com.lvjian.mybatis.chapter.spring.entity.User;
import com.lvjian.mybatis.chapter.spring.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean userRegister(User user) {
        String createTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .format(LocalDateTime.now());
        user.setCreateTime(createTime);
        if(userMapper.insert(user)>0) {
            return true;
        }
        return false;
    }

    @Override
    public List<User> getAllUserInfo() {
        return userMapper.getAllUserInfo();
    }

}
