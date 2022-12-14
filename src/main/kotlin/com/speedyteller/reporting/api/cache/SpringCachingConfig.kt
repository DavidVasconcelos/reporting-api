package com.speedyteller.reporting.api.cache

import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.CacheManager
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SpringCachingConfig {

    @Value("#{'\${caching.spring.list}'.split(';')}")
    private lateinit var cacheList: List<String>

    @Bean
    @Suppress("SpreadOperator")
    fun cacheManager(): CacheManager {
        return ConcurrentMapCacheManager(*cacheList.toTypedArray())
    }
}
