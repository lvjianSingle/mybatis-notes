package com.lvjian.mybaits.chapter.jdbc;

import org.junit.Test;

import java.sql.Driver;
import java.util.ServiceLoader;

/**
 * Title: SPIExample
 * Description: SPI示例
 *
 * @author lvjian
 * @version 1.0.0
 * @date 2021/6/22 21:28
 */
public class SPIExample {

    @Test
    public void testSPI() {
        ServiceLoader<Driver> drivers = ServiceLoader.load(java.sql.Driver.class);
        for (Driver driver : drivers ) {
            System.out.println(driver.getClass().getName());
        }
    }

}
