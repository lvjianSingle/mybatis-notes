package com.lvjian.mybatis.chapter;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Title: ScriptRunnerExample
 * Description: ScriptRunner使用示例代码
 *
 * @author lvjian
 * @version 1.0.0
 * @date 2021/6/22 22:12
 */
public class ScriptRunnerExample {

    @Test
    public void testScriptRunner() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem:mybatis","sa", "");

            ScriptRunner scriptRunner = new ScriptRunner(connection);
            //执行
            scriptRunner.runScript(Resources.getResourceAsReader("create-table.sql"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
