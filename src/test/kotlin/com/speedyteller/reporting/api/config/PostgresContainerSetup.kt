package com.speedyteller.reporting.api.config

import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy
import java.time.Duration
import java.time.temporal.ChronoUnit

class PostgresContainerSetup : ApplicationContextInitializer<ConfigurableApplicationContext> {

    override fun initialize(context: ConfigurableApplicationContext) {
        postgres.portBindings = listOf(POSTGRES_PORT)
        postgres.withDatabaseName(DATABASE_NAME)
        postgres.withUsername(POSTGRES)
        postgres.withPassword(POSTGRES)
        postgres.waitingFor(
            LogMessageWaitStrategy()
            .withRegEx(".*database system is ready to accept connections.*\\s")
            .withTimes(2).withStartupTimeout(Duration.of(60L, ChronoUnit.SECONDS)))
        postgres.start()

        System.setProperty("DB_URL", DB_URL)
        System.setProperty("DB_USERNAME", POSTGRES)
        System.setProperty("DB_PASSWORD", POSTGRES)
    }

    companion object {
        private const val POSTGRES_PORT = "5433:5432"
        private const val IMAGE_VERSION = "postgres:16.2"
        private const val POSTGRES = "postgres"
        private const val DATABASE_NAME = "report"
        private const val DB_URL =
            "jdbc:postgresql://localhost:5433/report?currentSchema=speedyteller&loggerLevel=OFF"

        val postgres: PostgreSQLContainer<*> = PostgreSQLContainer<Nothing>(IMAGE_VERSION)
    }
}
