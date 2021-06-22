package com.lvjian.mybatis.common;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * Title: DbUtils
 * Description: TODO 描述一下这个类是干嘛的
 *
 * @author lvjian
 * @version 1.0.0
 * @date 2021/6/21 18:35
 */
public abstract class DbUtils {

    public static Connection initData() {
        Connection conn = null;

        try {
            // 加载HSQLDB驱动
            Class.forName("org.hsqldb.jdbcDriver");
            // 获取Connection对象
            conn = DriverManager.getConnection("jdbc:hsqldb:mem:mybatis","sa", "");

            // 使用Mybatis的ScriptRunner工具类执行数据库脚本
            ScriptRunner scriptRunner = new ScriptRunner(conn);
            // 不输出sql日志
            scriptRunner.setLogWriter(null);
            scriptRunner.runScript(Resources.getResourceAsReader("create-table.sql"));
            scriptRunner.runScript(Resources.getResourceAsReader("init-data.sql"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }


    public static void dumpRS(ResultSet resultSet) throws Exception {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columCount = metaData.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columCount; i++) {
                String columName = metaData.getColumnName(i);
                String columVal = resultSet.getString(columName);
                System.out.println(columName + ":" + columVal);
            }
            System.out.println("-------------------------------------");
        }
    }

}
