package com.lvjian.mybaits.chapter;

import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.decorators.*;
import org.apache.ibatis.cache.impl.PerpetualCache;
import org.apache.ibatis.mapping.CacheBuilder;
import org.junit.Test;

/**
 * Title: CacheExample
 * Description: 缓存装饰类的使用案例
 *
 * @author lvjian
 * @version 1.0.0
 * @date 2021/6/26 17:50
 */
public class CacheExample {

    @Test
    public void testCache() {
        final int N = 100000;
        Cache cache = new PerpetualCache("default");
        cache = new LruCache(cache);
        cache = new FifoCache(cache);
        cache = new SoftCache(cache);
        cache = new WeakCache(cache);
        cache = new ScheduledCache(cache);
        cache = new SerializedCache(cache);
        cache = new SynchronizedCache(cache);
        cache = new TransactionalCache(cache);
        for (int i = 0; i < N; i++) {
            cache.putObject(i, i);
            ((TransactionalCache) cache).commit();
        }
        System.out.println(cache.getSize());
    }

    /**
     * CacheBuilder类，通过生成器模式创建缓存对象
     *
     * @author lvjian
     * @date 2021/6/26 17:51
     * @return void
     * @throws
     */
    @Test
    public void testCacheBuilder() {
        final int N = 100000;
        Cache cache = new CacheBuilder("com.lvjian.mybaits.chapter.mapper.UserMapper")
                .implementation(PerpetualCache.class)
                .addDecorator(LruCache.class)
                .clearInterval(10 * 60L)
                .size(1024)
                .readWrite(false)
                .blocking(false)
                .properties(null)
                .build();
        for (int i = 0; i < N; i++) {
            cache.putObject(i, i);
        }
        System.out.println(cache.getSize());
    }
}
