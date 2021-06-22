package com.lvjian.mybaits.chapter.jdbc;

import com.lvjian.mybatis.common.IOUtils;
import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Title: Example04
 * Description: PreparedStatement占位符执行SQL示例代码
 *
 * @author lvjian
 * @version 1.0.0
 * @date 2021-6-22 16:19:32
 */
public class Example06 {


    @Before
    public void initData() throws IOException, ClassNotFoundException, SQLException {
        // 初始化数据
        Class.forName("org.hsqldb.jdbcDriver");
        // 获取Connection对象
        Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:mybatis","sa", "");
        // 使用Mybatis的ScriptRunner工具类执行数据库脚本
        ScriptRunner scriptRunner = new ScriptRunner(conn);
        // 不输出sql日志
        scriptRunner.setLogWriter(null);
        scriptRunner.runScript(Resources.getResourceAsReader("create-table.sql"));
    }


    @Test
    public void testJdbcBatch() {
        try {
            // 创建DataSource实例
            DataSourceFactory dsf = new UnpooledDataSourceFactory();
            Properties properties = new Properties();
            InputStream configStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("database.properties");
            properties.load(configStream);
            dsf.setProperties(properties);
            DataSource dataSource = dsf.getDataSource();

            // 获取Connection对象
            Connection connection = dataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement("insert into  " +
                    "user(create_time, name, password, phone, nick_name) " +
                    "values(?,?,?,?,?);");
            stmt.setString(1,"2010-10-24 10:20:30");
            stmt.setString(2,"User1");
            stmt.setString(3,"test");
            stmt.setString(4,"18700001111");
            stmt.setString(5,"User1");
            ParameterMetaData pmd = stmt.getParameterMetaData();
            for(int i = 1; i <= pmd.getParameterCount(); i++) {
                String typeName = pmd.getParameterTypeName(i);
                String className = pmd.getParameterClassName(i);
                System.out.println("第" + i + "个参数，" + "typeName:" + typeName + ", className:" + className);
            }
            //执行SQL
            stmt.execute();

            // 关闭连接
            IOUtils.closeQuietly(stmt);
            IOUtils.closeQuietly(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
