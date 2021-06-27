package com.lvjian.mybatis.chapter.sqlsession;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

/**
 * Title: SqlSessionExample
 * Description: SqlSession创建示例代码
 *
 * @author lvjian
 * @version 1.0.0
 * @date 2021/6/25 0:08
 */
public class SqlSessionExample {

    @Test
    public void testSqlSession() throws IOException {
        // 获取Mybatis配置文件输入流
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        // 通过SqlSessionFactoryBuilder创建SqlSessionFactory实例
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        // 调用SqlSessionFactory的openSession（）方法，创建SqlSession实例
        SqlSession session = sqlSessionFactory.openSession();
    }

}
