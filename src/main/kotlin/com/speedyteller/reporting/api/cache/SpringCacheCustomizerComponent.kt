package com.speedyteller.reporting.api.cache

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.stereotype.Component

@Component
class SpringCacheCustomizerComponent : CacheManagerCustomizer<ConcurrentMapCacheManager> {

    @Value("#{'\${caching.spring.list}'.split(';')}")
    private lateinit var cacheList: List<String>
    override fun customize(cacheManager: ConcurrentMapCacheManager) {
        cacheManager.setCacheNames(cacheList)
    }
}
