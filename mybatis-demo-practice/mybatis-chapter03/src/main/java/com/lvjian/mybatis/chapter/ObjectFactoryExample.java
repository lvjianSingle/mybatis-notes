package com.lvjian.mybatis.chapter;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Title: ObjectFactoryExample
 * Description: ObjectFactory对象工厂类使用示例代码
 *
 * @author lvjian
 * @version 1.0.0
 * @date 2021/6/23 15:50
 */
public class ObjectFactoryExample {

    @Test
    public void testObjectFactory() {
        ObjectFactory objectFactory = new DefaultObjectFactory();
        List<Integer> list = objectFactory.create(List.class);
        Map<String,String> map = objectFactory.create(Map.class);
        list.addAll(Arrays.asList(1, 2, 3));
        map.put("test", "test");
        System.out.println(list);
        System.out.println(map);
    }

}
