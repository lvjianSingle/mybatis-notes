package com.lvjian.mybatis.chapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: MetaObjectExample
 * Description: MetaObject反射类使用示例代码
 *
 * <pre>
 *     MetaObject用于获取和设置对象的属性值
 * </pre>
 *
 * @author lvjian
 * @version 1.0.0
 * @date 2021/6/23 15:24
 */
public class MetaObjectExample {

    @Data
    @AllArgsConstructor
    private static class User {
        List<Order> orders;
        String name;
        Integer age;
    }

    @Data
    @AllArgsConstructor
    private static class Order {
        String orderNo;
        String goodsName;
    }

    @Test
    public void testMetaObject() {
        List<Order> orders = new ArrayList() {{
                add(new Order("order20210623152433", "《Mybatis源码深度解析》图书"));
                add(new Order("order20210623152438", "《AngularJS入门与进阶》图书"));}};

        User user = new User(orders, "赵子龙", 3);

        MetaObject metaObject = SystemMetaObject.forObject(user);

        // 获取第一笔订单的商品名称
        System.out.println(metaObject.getValue("orders[0].goodsName"));
        // 获取第二笔订单的商品名称
        System.out.println(metaObject.getValue("orders[1].goodsName"));
        //获取用户名称
        System.out.println(metaObject.getValue("name"));

        // 为属性设置值
        metaObject.setValue("orders[1].orderNo","order20210623152710");
        // 判断User对象是否有orderNo属性
        System.out.println("是否有orderNo属性且orderNo属性有对应的Getter方法：" + metaObject.hasGetter("orderNo"));
        // 判断User对象是否有name属性
        System.out.println("是否有name属性且orderNo属性有对应的name方法：" + metaObject.hasGetter("name"));
    }

}
