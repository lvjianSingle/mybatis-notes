package com.lvjian.mybaits.chapter.jdbc;

import com.lvjian.mybatis.common.DbUtils;
import com.lvjian.mybatis.common.IOUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

/**
 * Title: Example09
 * Description: 使用保存点回滚到指定的位置示例代码
 *
 * @author lvjian
 * @version 1.0.0
 * @date 2021/6/22 21:25
 */
public class Example09 {

    @Before
    public void initData() throws Exception {
        // 初始化数据
        Class.forName("org.hsqldb.jdbcDriver");
        // 获取Connection对象
        Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:mybatis",
                "sa", "");
        // 使用Mybatis的ScriptRunner工具类执行数据库脚本
        ScriptRunner scriptRunner = new ScriptRunner(conn);
        // 不输出sql日志
        scriptRunner.setLogWriter(null);
        scriptRunner.runScript(Resources.getResourceAsReader("create-table.sql"));
        System.out.println("------------------------");
    }

    @Test
    public void testSavePoint() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            // 获取Connection对象
            Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:mybatis","sa", "");

            String sql1 = "insert into user(create_time, name, password, phone, nick_name) " +
                    "values('2010-10-24 10:20:30','User1','test','18700001111','User1')";
            String sql2 = "insert into user(create_time, name, password, phone, nick_name) " +
                    "values('2010-10-24 10:20:30','User2','test','18700001111','User2')";
            //关闭自动提交事务
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql1);
            // 创建保存点
            Savepoint savepoint = conn.setSavepoint("SP1");
            //手动释放保存点 一旦保存点被释放，试图回滚到被释放的保存点时就将会抛出SQLException异常
//            conn.releaseSavepoint(savepoint);

            stmt.executeUpdate(sql2);
            // 回滚到保存点
            conn.rollback(savepoint);
            //手动提交事务
            conn.commit();

            ResultSet rs = conn.createStatement().executeQuery("select * from user ");

            DbUtils.dumpRS(rs);
            IOUtils.closeQuietly(stmt);
            IOUtils.closeQuietly(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
