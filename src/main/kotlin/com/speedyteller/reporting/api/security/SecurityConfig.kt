package com.speedyteller.reporting.api.security

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
class SecurityConfig(
    val userDetailsService: UserDetailsService,
    val jwtTokenFilter: JwtTokenFilter
) : WebSecurityConfigurerAdapter() {

    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    init {
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL)
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(UserDetailsService { username: String? ->
            userDetailsService
                .loadUserByUsername(username)
        })
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }

    override fun configure(httpSecurity: HttpSecurity) {

        // Enable CORS and disable CSRF
        var http = httpSecurity
        http = http.cors().and().csrf().disable()
        // Set session management to stateless
        http = http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
        // Set unauthorized requests exception handler
        http = http
            .exceptionHandling()
            .authenticationEntryPoint { _: HttpServletRequest?,
                                        response: HttpServletResponse,
                                        ex: AuthenticationException ->
                logger.error("Unauthorized request - {}", ex.message)
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.message)
            }
            .and()
        // Set authorized requests
        http.authorizeRequests()
            .antMatchers("/").permitAll()
            // Public endpoints
            .antMatchers(
                "/health",
                "/merchant/**",
                "/openapi.yaml",
                "/openapi/**",
                "/swagger-ui.html",
                "/swagger-ui/**"
            ).permitAll()
            // Private endpoints
            .anyRequest().authenticated()
        // Add JWT token filter
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter::class.java)
    }

    @Bean
    fun corsFilter(): CorsFilter? {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.allowCredentials = true
        config.addAllowedOrigin("*")
        config.addAllowedHeader("*")
        config.addAllowedMethod("*")
        source.registerCorsConfiguration("/**", config)
        return CorsFilter(source)
    }

    @Bean
    @Throws(java.lang.Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager? {
        return super.authenticationManagerBean()
    }
}
