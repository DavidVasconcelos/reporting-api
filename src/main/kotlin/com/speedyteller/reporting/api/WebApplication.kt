package com.speedyteller.reporting.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.scheduling.annotation.EnableScheduling

@EnableCaching
@EnableScheduling
@SpringBootApplication
class WebApplication

fun main(args: Array<String>) {
	runApplication<WebApplication> {
		if (args.isNotEmpty() && args[0] == "migrate") {
			this.setAdditionalProfiles("dbmigration")
		}
	}
}
