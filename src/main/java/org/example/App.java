package org.example;

import org.infinispan.Cache;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.eviction.EvictionType;
import org.infinispan.manager.DefaultCacheManager;

import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {

        //no need infinispan-config.xml if use commented cache manager

        DefaultCacheManager cacheManager = new DefaultCacheManager();
        cacheManager.defineConfiguration("local", new ConfigurationBuilder()
                //max cache data size
                .memory().evictionType(EvictionType.COUNT).size(1)
                //all cache data lifespan
                .expiration().lifespan(5, TimeUnit.SECONDS)
                .build());

        //DefaultCacheManager cacheManager = new DefaultCacheManager("infinispan-config.xml");
        Cache<String, String> cache = cacheManager.getCache("local");

        cache.addListener(new AppListener());

        cache.put("student1", "john");
        cache.put("student2", "farrukh", 3, TimeUnit.SECONDS);
        cache.put("student1", "john");

        //clear all data
        //cache.entrySet().clear();

        System.out.println("//student1 = "+ cache.get("student1"));
        System.out.println("//student2 = "+ cache.get("student2"));

        Thread.sleep(6000);

        System.out.println("//student1 = "+ cache.get("student1"));
        System.out.println("//student2 = "+ cache.get("student2"));

        cacheManager.stop();
    }
}
