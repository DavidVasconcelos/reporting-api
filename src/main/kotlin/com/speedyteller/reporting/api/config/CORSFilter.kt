package com.speedyteller.reporting.api.config

import com.speedyteller.reporting.api.domain.constant.BusinessConstants
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Classe CORSFilter - representa a configuracao de CORSFilter
 * @author Ben Visa Vale 2019
 * @version 1.0
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class CORSFilter : Filter {

    @Throws(ServletException::class)
    override fun init(filterConfig: FilterConfig) {

        //init
    }

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest, res: ServletResponse, chain: FilterChain) {

        val response = res as HttpServletResponse
        response.setHeader("Access-Control-Allow-Origin", "*")
        response.setHeader("Access-Control-Allow-Credentials", "false")
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, PATCH, DELETE")
        response.setHeader("Access-Control-Allow-Headers", "*")
        response.setHeader("Access-Control-Max-Age", "3600")

        if ("OPTIONS".equals((request as HttpServletRequest).method, ignoreCase = true)) {
            response.status = BusinessConstants.HTTP_OK
        } else {
            chain.doFilter(request, response)
        }
    }

    override fun destroy() {

        //destroy
    }
}
