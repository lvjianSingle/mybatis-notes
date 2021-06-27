package com.lvjian.mybatis.chapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.ibatis.executor.loader.ProxyFactory;
import org.apache.ibatis.executor.loader.ResultLoaderMap;
import org.apache.ibatis.executor.loader.cglib.CglibProxyFactory;
import org.apache.ibatis.executor.loader.javassist.JavassistProxyFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.session.Configuration;
import org.junit.Test;

import java.util.Arrays;

import static org.mockito.Mockito.mock;

/**
 * Title: ProxyFactoryExample
 * Description: ProxyFactory代理工厂类使用示例代码
 *
 * @author lvjian
 * @version 1.0.0
 * @date 2021/6/23 16:08
 */
public class ProxyFactoryExample {

    @Data
    @AllArgsConstructor
    private static class Order {
        private String orderNo;
        private String goodsName;
    }

    @Test
    public void testJavassistProxyFactory() {
        // 创建ProxyFactory对象
        ProxyFactory proxyFactory = new JavassistProxyFactory();

        Order order = new Order("gn20170123","《Mybatis源码深度解析》图书");

        //创建ObjectFactory对象用于创建生成对象
        ObjectFactory objectFactory = new DefaultObjectFactory();

        // 调用ProxyFactory对象的createProxy（）方法创建代理对象
        Object proxyOrder = proxyFactory.createProxy(order
                ,mock(ResultLoaderMap.class)
                ,mock(Configuration.class)
                ,objectFactory
                , Arrays.asList(String.class,String.class)
                ,Arrays.asList(order.getOrderNo(),order.getGoodsName())
        );

        System.out.println(proxyOrder.getClass());
        System.out.println(((Order)proxyOrder).getGoodsName());
    }

    @Test
    public void testCglibProxyFactory() {
        // 创建ProxyFactory对象
        ProxyFactory proxyFactory = new CglibProxyFactory();

        Order order = new Order("gn20170123","《Mybatis源码深度解析》图书");

        //创建ObjectFactory对象用于创建生成对象
        ObjectFactory objectFactory = new DefaultObjectFactory();

        // 调用ProxyFactory对象的createProxy（）方法创建代理对象
        Object proxyOrder = proxyFactory.createProxy(order
                ,mock(ResultLoaderMap.class)
                ,mock(Configuration.class)
                ,objectFactory
                , Arrays.asList(String.class,String.class)
                ,Arrays.asList(order.getOrderNo(),order.getGoodsName())
        );

        System.out.println(proxyOrder.getClass());
        System.out.println(((Order)proxyOrder).getGoodsName());
    }

}
