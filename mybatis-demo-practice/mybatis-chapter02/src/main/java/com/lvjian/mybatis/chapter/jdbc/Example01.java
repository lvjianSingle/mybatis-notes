package com.lvjian.mybatis.chapter.jdbc;

import com.lvjian.mybatis.common.DbUtils;
import com.lvjian.mybatis.common.IOUtils;
import org.junit.Test;

import java.sql.*;

/**
 * Title: Example01
 * Description: DriverManager获取Connection对象示例代码
 *
 * <pre>
 *     DriverManager：这是一个在JDBC 1.0规范中就已经存在、完全由JDBC API实现的驱动管理类。
 *     当应用程序第一次尝试通过URL连接数据源时，DriverManager会自动加载CLASSPATH下所有的JDBC驱动。
 *     DriverManager类提供了一系列重载的getConnection()方法，用来获取Connection对象
 * </pre>
 *
 * @author lvjian
 * @version 1.0.0
 * @date 2021/6/21 18:30
 */
public class Example01 {

    @Test
    public void testJdbc() {
        // 初始化数据
        DbUtils.initData();
        try {
            // 加载驱动
            Class.forName("org.hsqldb.jdbcDriver");
            // 获取Connection对象
            Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem:mybatis","sa", "");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user");

            // 遍历ResultSet
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    String columnVal = resultSet.getString(columnName);
                    System.out.println(columnName + ":" + columnVal);
                }
                System.out.println("--------------------------------------");
            }

            // 关闭连接
            IOUtils.closeQuietly(statement);
            IOUtils.closeQuietly(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
