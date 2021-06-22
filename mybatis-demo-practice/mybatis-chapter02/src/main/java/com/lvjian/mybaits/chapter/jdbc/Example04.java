package com.lvjian.mybaits.chapter.jdbc;

import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Title: Example04
 * Description: JNDI
 *
 * @author lvjian
 * @version 1.0.0
 * @date 2021/6/21 22:00
 */
public class Example04 {


    @Before
    public void before() throws IOException {
        //创建数据源工厂类
        DataSourceFactory dataSourceFactory = new UnpooledDataSourceFactory();

        Properties properties = new Properties();
        //读取数据库配置文件数据
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("database.properties");
        //加载数据库配置文件
        properties.load(inputStream);

        dataSourceFactory.setProperties(properties);

        //获取数据源对象
        DataSource dataSource = dataSourceFactory.getDataSource();

        try {
            Properties jndiProps = new Properties();
            jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
            jndiProps.put(Context.URL_PKG_PREFIXES, "org.apache.naming");

            InitialContext context = new InitialContext(jndiProps);
            context.bind("java:TestDC", dataSource);
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void testJndi() {
        try {
            Properties jndiProps = new Properties();
            jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
            jndiProps.put(Context.URL_PKG_PREFIXES, "org.apache.naming");

            InitialContext context = new InitialContext(jndiProps);

            DataSource dataSource = (DataSource) context.lookup("java:TestDC");

            Connection connection = dataSource.getConnection();
            Assert.assertNotNull(connection);
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

    }


}
