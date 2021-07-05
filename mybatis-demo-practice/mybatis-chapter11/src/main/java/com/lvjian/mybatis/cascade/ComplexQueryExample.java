package com.lvjian.mybatis.cascade;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lvjian.mybatis.cascade.entity.Order;
import com.lvjian.mybatis.cascade.entity.User;
import com.lvjian.mybatis.cascade.mapper.OrderMapper;
import com.lvjian.mybatis.cascade.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

/**
 * Title: ComplexQueryExample
 * Description: 级联映射与懒加载
 *
 * @author lvjian
 * @version 1.0.0
 * @date 2021/7/5 15:51
 */
public class ComplexQueryExample {

    private SqlSession sqlSession;

    private UserMapper userMapper;

    private OrderMapper orderMapper;

    @Before
    public void init() throws IOException {
        // 初始化测试数据
        initData();
        // 获取配置文件输入流
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 通过SqlSessionFactoryBuilder的build()方法创建SqlSessionFactory实例
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 调用openSession()方法创建SqlSession实例
        sqlSession = sqlSessionFactory.openSession();
        // 获取UserMapper代理对象
        userMapper = sqlSession.getMapper(UserMapper.class);
        orderMapper = sqlSession.getMapper(OrderMapper.class);
    }

    @Test
    public void testSimpleQuery() {
        User user = userMapper.getUserById(1L);
        System.out.println(JSON.toJSONString(user));
        List<Order> orders = orderMapper.listOrdersByUserId(1L);
        System.out.println(JSON.toJSONString(orders));
    }

    @Test
    public void testGetUserByIdForUserDetailMap() {
        User user = userMapper.getUserByIdForUserDetailMap(1L);
        System.out.println(JSON.toJSONString(user));
    }

    @Test
    public void testGetUserByIdForDiscriminator() {
        User femaleUser = userMapper.getUserByIdForDiscriminator(2L);
        System.out.println(JSON.toJSONString(femaleUser));
        User maleUser = userMapper.getUserByIdForDiscriminator(1L);
        System.out.println(JSON.toJSONString(maleUser));
    }

    @Test
    public void testGetUserByIdForJoin() {
        User user = userMapper.getUserByIdForJoin(2L);
        System.out.println(JSON.toJSONString(user));
    }

    @Test
    public void testOne2ManyQuery() {
        User user = userMapper.getUserByIdFull(1L);
        System.out.println(JSON.toJSONString(user));
    }

    @Test
    public void testOne2OneQuery() {
       Order order =  orderMapper.getOrderByNo("order_2314234");
       System.out.println(JSON.toJSONString(order));
    }

    @Test
    public void testGetOrderByNoWithJoin() {
        Order order = orderMapper.getOrderByNoWithJoin("order_2314234");
        System.out.println(JSON.toJSONString(order));
    }

    @Test
    public void testLazyQuery() {
        Order order =  orderMapper.getOrderByNo("order_2314234");
        System.out.println("完成Order数据查询");
        // 调用getUser（）方法时执行懒加载
        order.getUser();
    }

    @Test
    public void testGetUsersByPhone() {
        List<User> users = userMapper.getUsersByPhone("18700001111");
        System.out.println(users);

        List<User> users2 = userMapper.getUsersByPhone("18700001111");
        System.out.println(users2);

    }

    private void initData() {
        try {
            // 获取Connection对象
            Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:mybatis","sa", "");
            // 使用Mybatis的ScriptRunner工具类执行数据库脚本
            ScriptRunner scriptRunner = new ScriptRunner(conn);
            // 不输出sql日志
            scriptRunner.setLogWriter(null);
            scriptRunner.runScript(Resources.getResourceAsReader("create-table-c11.sql"));
            scriptRunner.runScript(Resources.getResourceAsReader("init-data-c11.sql"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
