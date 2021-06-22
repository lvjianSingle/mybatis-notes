package com.lvjian.mybaits.chapter.jdbc;

import com.lvjian.mybatis.common.DbUtils;
import com.lvjian.mybatis.common.IOUtils;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/**
 * Title: Example01
 * Description: MyBatis的DataSource实例获取Connection对象示例代码
 *
 * <pre>
 *     这个接口是在JDBC 2.0规范可选包中引入的API。
 *     它比DriverManager更受欢迎，因为它提供了更多底层数据源相关的细节，而且对应用来说，不需要关注JDBC驱动的实现。
 *     一个DataSource对象的属性被设置后，它就代表一个特定的数据源。
 *     当DataSource实例的getConnection()方法被调用后，DataSource实例就会返回一个与数据源建立连接的Connection对象。
 *     在应用程序中修改DataSource对象的属性后，就可以通过DataSource对象获取指向不同数据源的Connection对象。
 *     同样，数据源的具体实现修改后，不需要修改应用程序代码。
 *     需要注意的是，JDBC API中只提供了DataSource接口，没有提供DataSource的具体实现，DataSource具体的实现由JDBC驱动程序提供。
 *     另外，目前一些主流的数据库连接池（例如DBCP、C3P0、Druid等）也提供了DataSource接口的具体实现。
 * </pre>
 *
 * @author lvjian
 * @version 1.0.0
 * @date 2021/6/21 18:30
 */
public class Example02 {

    @Test
    public void testJdbc() {
        // 初始化数据
        DbUtils.initData();
        try {
            // 创建DataSource实例
            DataSource dataSource = new UnpooledDataSource("org.hsqldb.jdbcDriver","jdbc:hsqldb:mem:mybatis", "sa", "");
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
                System.out.println("---------------------------------------");
            }

            // 关闭连接
            IOUtils.closeQuietly(statement);
            IOUtils.closeQuietly(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
