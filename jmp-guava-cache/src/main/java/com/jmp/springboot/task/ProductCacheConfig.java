package com.jmp.springboot.task;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Configuration
public class ProductCacheConfig {

    @Autowired
    ProductRepository productRepository;

    private LoadingCache<String, Product> loadingCache = CacheBuilder.newBuilder().refreshAfterWrite(20, TimeUnit.SECONDS).recordStats().maximumSize(5).
            build(new CacheLoader<String, Product>() {

                @Override
                public Product load(String key) throws Exception {
                    Thread.sleep(1000 * 5);
                    return productRepository.getProductById(key);
                }

            });

    public Product getProduct(String key) throws ExecutionException {
        return loadingCache.get(key);
    }

    public CacheStats getCacheStats() {
        return loadingCache.stats();
    }
}
