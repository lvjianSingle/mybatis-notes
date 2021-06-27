package com.lvjian.mybatis.chapter;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.junit.Test;

/**
 * Title: LogExample
 * Description: 日志示例代码
 *
 * @author lvjian
 * @version 1.0.0
 * @date 2021/6/27 16:57
 */
public class LogExample {

    @Test
    public void testLog0() {
        Log log = LogFactory.getLog(LogExample.class);
        log.error("LogExample.testLog0 function.");
    }

    @Test
    public void testLog() {
        // 指定使用Log4j 框架输出日志
        LogFactory.useLog4JLogging();
        // 获取Log实例
        Log log = LogFactory.getLog(LogExample.class);
        // 输出日志
        log.error("LogExample.testLog function.");
    }

}
