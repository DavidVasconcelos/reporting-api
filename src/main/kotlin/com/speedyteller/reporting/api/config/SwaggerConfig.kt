package com.speedyteller.reporting.api.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger.web.DocExpansion
import springfox.documentation.swagger.web.ModelRendering
import springfox.documentation.swagger.web.OperationsSorter
import springfox.documentation.swagger.web.TagsSorter
import springfox.documentation.swagger.web.UiConfiguration
import springfox.documentation.swagger.web.UiConfigurationBuilder
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.time.LocalDate
import java.time.LocalDateTime


@EnableSwagger2
@Configuration
class SwaggerConfig {

    @Value("\${info.app.name}")
    private val apiName = "Api Documentation"

    @Value("\${info.app.description}")
    private val apiDescription = "Api Documentation"

    @Value("\${info.build.version}")
    private val buildVersion: String? = null

    @Bean
    fun swaggerSpringfoxDocket(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .genericModelSubstitutes(ResponseEntity::class.java)
                .forCodeGeneration(true)
                .genericModelSubstitutes(ResponseEntity::class.java)
                .directModelSubstitute(LocalDate::class.java, String::class.java)
                .directModelSubstitute(LocalDateTime::class.java, String::class.java)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController::class.java))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
    }

    @Bean
    fun uiConfig(): UiConfiguration {
        return UiConfigurationBuilder.builder()
                .deepLinking(true)
                .displayOperationId(false)
                .defaultModelsExpandDepth(1)
                .defaultModelExpandDepth(1)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(true)
                .docExpansion(DocExpansion.NONE)
                .filter(false)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(false)
                .tagsSorter(TagsSorter.ALPHA)
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                .validatorUrl(null)
                .build()
    }

    fun apiInfo(): ApiInfo {
        return ApiInfoBuilder().title(apiName).description(apiDescription).version(buildVersion).build()
    }
}
