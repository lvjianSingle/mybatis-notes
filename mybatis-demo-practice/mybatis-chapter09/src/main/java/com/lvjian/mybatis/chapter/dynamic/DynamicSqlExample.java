package com.lvjian.mybatis.chapter.dynamic;

import com.alibaba.fastjson.JSON;
import com.lvjian.mybatis.chapter.dynamic.entity.UserEntity;
import com.lvjian.mybatis.chapter.dynamic.mapper.UserMapper;
import com.lvjian.mybatis.common.DbUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.scripting.xmltags.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title: DynamicSqlExample
 * Description: 动态SQL示例代码
 *
 * @author lvjian
 * @version 1.0.0
 * @date 2021/6/27 18:23
 */
public class DynamicSqlExample {

    private UserMapper userMapper;

    private SqlSession sqlSession;

    @Before
    public void initData() throws IOException {
        DbUtils.initData();
        // 获取配置文件输入流
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 通过SqlSessionFactoryBuilder的build()方法创建SqlSessionFactory实例
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 调用openSession()方法创建SqlSession实例
        sqlSession = sqlSessionFactory.openSession();
        // 获取UserMapper代理对象
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    /**
     * 测试<where><where/>的使用
     */
    @Test
    public void testWhereDynamicSql()  {
        UserEntity entity = new UserEntity();
        entity.setPhone("18700001111");
        entity.setName("User1");
        List<UserEntity> userList =  userMapper.getUserByEntity(entity);
        System.out.println(JSON.toJSONString(userList));
    }

    /**
     * <choose|when|otherwise>：这几个标签需要组合使用，类似于Java中的switch语法
     */
    @Test
    public void testChooseDynamicSql() {
        UserEntity entity = new UserEntity();
        entity.setPhone("18700001111");
        entity.setName("User1");
        List<UserEntity> userList = userMapper.getUserInfo(entity);
        System.out.println(JSON.toJSONString(userList));
    }


    /**
     * 测试<foreach><foreach/>的使用
     */
    @Test
    public void testForeachDynamicSql() {
        List<UserEntity> entityList = userMapper.getUserByPhones(Arrays.asList("18700001111", "18700001112"));
        System.out.println(JSON.toJSONString(entityList));
    }

    @Test
    public void testGetUserByPhoneAndName() {
        UserEntity userEntity = userMapper.getUserByPhoneAndName("18700001111", "User4");
        System.out.println(userEntity);
    }

    @Test
    public void testGetUserByNames() {
        List<UserEntity> users = userMapper.getUserByNames(Arrays.asList("User4","User5"));
        System.out.println(users);
    }

    @Test
    public void testSqlNode() {
        /**
         *     select * from user where 1 = 1
         *         <if test="id != null">
         *             AND id = #{id}
         *         </if>
         *         <if test="name != null">
         *             AND name = #{name}
         *         </if>
         *         <if test="phone != null">
         *             AND phone = #{phone}
         *         </if>
         *
         *  转换为SqlNode代码示例：↓
         */

        // 构建SqlNode
        SqlNode sn1 = new StaticTextSqlNode("select * from user where 1=1");
        SqlNode sn2 = new IfSqlNode(new StaticTextSqlNode(" AND id = #{id}"),"id != null");
        SqlNode sn3 = new IfSqlNode(new StaticTextSqlNode(" AND name = #{name}"),"name != null");
        SqlNode sn4 = new IfSqlNode(new StaticTextSqlNode(" AND phone = #{phone}"),"phone != null");
        SqlNode mixedSqlNode = new MixedSqlNode(Arrays.asList(sn1, sn2, sn3, sn4));
        // 创建参数对象
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id","1");
        // 创建动态SQL解析上下文
        DynamicContext context = new DynamicContext(sqlSession.getConfiguration(),paramMap);
        // 调用SqlNode的apply()方法解析动态SQL
        mixedSqlNode.apply(context);
        // 调用DynamicContext对象的getSql（）方法获取动态SQL解析后的SQL语句
        System.out.println(context.getSql());
    }


    @Test
    public void testGetUserByName() {
        String userName = "'Test4'";
        UserEntity userEntity = userMapper.getUserByName(userName);
        System.out.println(userEntity);
    }


}
