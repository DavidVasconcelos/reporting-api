package com.speedyteller.reporting.api.cache

import org.springframework.cache.CacheManager
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SpringCachingConfig {

    @Bean
    fun cacheManager(): CacheManager {
        return ConcurrentMapCacheManager("logins", "users", "transactions", "customers")
    }
}
