package com.lvjian.mybatis.chapter.plugin.plugin;

import lombok.Singular;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.util.Properties;

/**
 * Title: TestInterceptor
 * Description: demo
 *
 * @author lvjian
 * @version 1.0.0
 * @date 2021/7/3 18:07
 */
@Intercepts({
        @Signature(method = "query", type = Executor.class, args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
//        @Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class,Integer.class})
})
//@Intercepts({})
public class TestInterceptor implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("执行了TestInterceptor");
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

}
