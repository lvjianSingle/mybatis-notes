package com.lvjian.mybatis.chapter.configuration;

import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

/**
 * Title: ConfigurationExample
 * Description: Configuration创建示例代码
 *
 * @author lvjian
 * @version 1.0.0
 * @date 2021/6/24 23:50
 */
public class ConfigurationExample {


    @Test
    public void testConfiguration() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        // 创建XMLConfigBuilder实例
        XMLConfigBuilder builder = new XMLConfigBuilder(reader);
        // 调用XMLConfigBuilder.parse（）方法，解析XML创建Configuration对象
        Configuration conf = builder.parse();
    }

}
