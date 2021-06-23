package com.lvjian.mybaits.chapter;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaClass;
import org.apache.ibatis.reflection.invoker.Invoker;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * Title: MetaClassExample
 * Description: MetaClass反射类使用示例代码
 *
 * <pre>
 *   MetaClass用于获取类相关的信息
 * </pre>
 *
 * @author lvjian
 * @version 1.0.0
 * @date 2021/6/23 15:36
 */
public class MetaClassExample {


    @Data
    @AllArgsConstructor
    private static class Order {
        String orderNo;
        String goodsName;
    }

    @Test
    public void testMetaClass() {
        MetaClass metaClass = MetaClass.forClass(Order.class, new DefaultReflectorFactory());
        // 获取所有有Getter方法的属性名
        String[] getterNames = metaClass.getGetterNames();
        System.out.println(JSON.toJSONString(getterNames));
        // 是否有默认构造方法
        System.out.println("是否有默认构造方法：" + metaClass.hasDefaultConstructor());
        // 某属性是否有对应的Getter/Setter方法
        System.out.println("orderNo属性是否有对应的Getter方法：" + metaClass.hasGetter("orderNo"));
        System.out.println("orderNo属性是否有对应的Setter方法：" + metaClass.hasSetter("orderNo"));

        System.out.println("orderNo属性类型：" + metaClass.getGetterType("orderNo"));

        // 获取属性Getter方法
        Invoker invoker = metaClass.getGetInvoker("orderNo");
        try {
            // 通过Invoker对象调用Getter方法获取属性值
            Object orderNo = invoker.invoke(new Order("order20171024010248","《Mybatis源码深度解析》图书"), null);
            System.out.println(orderNo);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }


}
