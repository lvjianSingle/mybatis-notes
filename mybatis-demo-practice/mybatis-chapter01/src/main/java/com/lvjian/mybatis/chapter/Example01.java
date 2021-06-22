package com.lvjian.mybatis.chapter;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.jdbc.SqlRunner;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Title: Example01
 * Description: HSQLDB简单使用示例
 *
 * @author lvjian
 * @version 1.0.0
 * @date 2021/6/21 16:41
 */
public class Example01 {

    private Connection connection = null;


    @Before
    public void initData() {
        try {
            // 加载HSQLDB驱动
            Class.forName("org.hsqldb.jdbcDriver");

            // 获取Connection对象
            connection = DriverManager.getConnection("jdbc:hsqldb:mem:mybatis","sa", "");

            // 使用Mybatis的ScriptRunner工具类执行数据库脚本
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            scriptRunner.setLogWriter(null);
            scriptRunner.runScript(Resources.getResourceAsReader("create-table.sql"));
            scriptRunner.runScript(Resources.getResourceAsReader("init-data.sql"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void testHsqldbQuery() {
        // SqlRunner是Mybatis封装的操作数据库的工具类
        SqlRunner sqlRunner = new SqlRunner(connection);

        try {
            //调用SqlRunner类的selectAll()方法查询数据
            List<Map<String, Object>> results = sqlRunner.selectAll("select * from user");
//            List<Map<String, Object>> results = sqlRunner.selectAll("select * from user where name = 'User1'");
            results.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sqlRunner.closeConnection();
        }

    }


}
