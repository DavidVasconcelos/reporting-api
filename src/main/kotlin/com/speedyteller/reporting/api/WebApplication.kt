package com.speedyteller.reporting.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebApplication

fun main(args: Array<String>) {
	runApplication<WebApplication> {
		if (args.isNotEmpty() && args[0] == "migrate") {
			this.setAdditionalProfiles("dbmigration")
		}
	}
}
