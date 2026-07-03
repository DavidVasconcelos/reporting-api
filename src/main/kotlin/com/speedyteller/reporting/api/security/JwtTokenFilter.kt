package com.speedyteller.reporting.api.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtTokenFilter(private val jwtTokenComponent: JwtTokenComponent) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val token = request.getHeader(HttpHeaders.AUTHORIZATION)

        if (token.isNullOrEmpty() || !jwtTokenComponent.validate(token)) {
            filterChain.doFilter(request, response)
            return
        }

        val username = jwtTokenComponent.getUsername(token)
        val roles = jwtTokenComponent.getRoles(token)
        val authorities = roles.map { SimpleGrantedAuthority(it) }

        val principal = User(username, "", authorities)

        val authentication = UsernamePasswordAuthenticationToken(principal, null, authorities)
        authentication.details = WebAuthenticationDetailsSource().buildDetails(request)

        SecurityContextHolder.getContext().authentication = authentication
        filterChain.doFilter(request, response)
    }
}
