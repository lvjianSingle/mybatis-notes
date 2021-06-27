package com.lvjian.mybatis.chapter.entity;

import lombok.Data;

import java.util.Date;

/**
 * Title: UserEntity
 * Description: UserEntity
 *
 * @author lvjian
 * @version 1.0.0
 * @date 2021/6/23 20:37
 */
@Data
public class UserEntity {
    private Long id;
    private String name;
    private Date createTime;
    private String password;
    private String phone;
    private String nickName;
}
