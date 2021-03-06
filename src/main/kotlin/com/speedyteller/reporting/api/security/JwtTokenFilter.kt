package com.speedyteller.reporting.api.security

import com.speedyteller.reporting.api.config.JwtTokenComponent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtTokenFilter : OncePerRequestFilter() {

    @Autowired
    private lateinit var jwtTokenComponent: JwtTokenComponent

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val header = request.getHeader(HttpHeaders.AUTHORIZATION)

        if (StringUtils.isEmpty(header)) {
            filterChain.doFilter(request, response)
            return
        }

        if (!jwtTokenComponent.validate(header)) {
            filterChain.doFilter(request, response)
            return
        }

        val userDetails: UserDetails = userDetailsService.loadUserByUsername(jwtTokenComponent.getUsername(header))

        val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)

        authentication.details = WebAuthenticationDetailsSource().buildDetails(request)

        SecurityContextHolder.getContext().authentication = authentication

        filterChain.doFilter(request, response)
    }
}
