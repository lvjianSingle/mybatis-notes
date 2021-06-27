package com.lvjian.mybaits.chapter;

import com.alibaba.fastjson.JSON;
import com.lvjian.mybaits.chapter.entity.UserEntity;
import com.lvjian.mybaits.chapter.mapper.UserMapper;
import com.lvjian.mybatis.common.DbUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Title: MybatisCacheExample
 * Description: TODO 描述一下这个类是干嘛的
 *
 * @author lvjian
 * @version 1.0.0
 * @date 2021/6/26 17:00
 */
public class MybatisCacheExample {

    @Before
    public void initData() {
        DbUtils.initData();
    }

    @Test
    public void testMybatisCache() throws IOException {
        // 获取配置文件输入流
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 通过SqlSessionFactoryBuilder的build()方法创建SqlSessionFactory实例
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 调用openSession()方法创建SqlSession实例
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 获取UserMapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 执行Mapper方法，获取执行结果
        // 观察控制台日志，此时会去数据库查询数据
        List<UserEntity> userList = userMapper.listAllUser();
        System.out.println("第一次查询:" + JSON.toJSONString(userList));

        // 执行Mapper方法，获取执行结果
        // 观察控制台日志，此时会在缓存中获取数据，不再查询数据库
        List<UserEntity> listAllUser = sqlSession.getMapper(UserMapper.class).listAllUser();
        System.out.println("第二次查询:" + JSON.toJSONString(listAllUser));
    }

}
