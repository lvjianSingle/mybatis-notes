package com.lvjian.mybatis.chapter.jdbc;

import com.lvjian.mybatis.common.DbUtils;
import com.lvjian.mybatis.common.IOUtils;
import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Properties;

/**
 * Title: Example01
 * Description:DataSourceFactory获取Connection对象示例代码
 *
 *
 * @author lvjian
 * @version 1.0.0
 * @date 2021/6/21 18:30
 */
public class Example03 {

    @Test
    public void testJdbc() {
        // 初始化数据
        DbUtils.initData();

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
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user");
            // 遍历ResultSet
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columCount = metaData.getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= columCount; i++) {
                    String columName = metaData.getColumnName(i);
                    String columVal = resultSet.getString(columName);
                    System.out.println(columName + ":" + columVal);
                }
                System.out.println("----------------------------------------");
            }

            // 关闭连接
            IOUtils.closeQuietly(statement);
            IOUtils.closeQuietly(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
