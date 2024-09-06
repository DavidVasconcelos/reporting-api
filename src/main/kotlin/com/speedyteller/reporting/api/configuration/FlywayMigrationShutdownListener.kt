package com.speedyteller.reporting.api.configuration

import org.springframework.boot.ExitCodeGenerator
import org.springframework.boot.SpringApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Profile
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Profile("dbmigration")
@Component
class FlywayMigrationShutdownListener(private val context: ApplicationContext) {

    @EventListener
    fun onApplicationReadyEvent(event: ApplicationReadyEvent) {
        println("Application ready in ${event.timeTaken.toSeconds()} seconds")
        println("Flyway migration completed. Shutting down.")
        SpringApplication.exit(context, ExitCodeGenerator { 0 })
    }
}