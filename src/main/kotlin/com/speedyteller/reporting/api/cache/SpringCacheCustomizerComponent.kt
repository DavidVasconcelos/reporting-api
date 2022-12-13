package com.speedyteller.reporting.api.cache

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.stereotype.Component

@Component
class SpringCacheCustomizerComponent : CacheManagerCustomizer<ConcurrentMapCacheManager> {
    override fun customize(cacheManager: ConcurrentMapCacheManager) {
        cacheManager.setCacheNames(listOf("logins", "users", "transactions", "customers"))
    }
}
