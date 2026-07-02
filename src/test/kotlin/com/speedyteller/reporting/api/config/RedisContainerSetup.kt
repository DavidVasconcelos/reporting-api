package com.speedyteller.reporting.api.config

import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.GenericContainer
import org.testcontainers.utility.DockerImageName

class RedisContainerSetup : ApplicationContextInitializer<ConfigurableApplicationContext> {

    override fun initialize(context: ConfigurableApplicationContext) {
        redis.start()
        System.setProperty("spring.data.redis.host", redis.host)
        System.setProperty("spring.data.redis.port", redis.firstMappedPort.toString())
        System.setProperty("REDIS_HOST", redis.host)
        System.setProperty("REDIS_PORT", redis.firstMappedPort.toString())
    }

    companion object {
        private const val IMAGE_VERSION = "redis:7.4"
        private const val REDIS_INTERNAL_PORT = 6379

        val redis: GenericContainer<*> = GenericContainer(DockerImageName.parse(IMAGE_VERSION))
            .withExposedPorts(REDIS_INTERNAL_PORT)
    }
}
