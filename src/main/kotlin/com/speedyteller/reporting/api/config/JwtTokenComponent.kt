package com.speedyteller.reporting.api.config

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.SignatureException
import io.jsonwebtoken.UnsupportedJwtException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import java.lang.String.format
import java.util.Date

@Component
class JwtTokenComponent {

    private var logger: Logger = LoggerFactory.getLogger(JwtTokenComponent::class.java)

    @Value("\${jwt.secret}")
    private val secret: String? = null

    @Value("\${jwt.issuer}")
    private val issuer: String? = null

    fun generateAccessToken(user: User): String? {
        return Jwts.builder()
            .setSubject(user.username)
            .setIssuer(issuer)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + 10 * 60 * 1000)) // 10 minutes
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact()
    }

    fun getUsername(token: String?): String? {
        val claims: Claims = Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .body
        return claims.subject
    }

    fun validate(token: String?): Boolean {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
            return true
        } catch (ex: SignatureException) {
            logger.error("Invalid JWT signature - {}", ex.message)
        } catch (ex: MalformedJwtException) {
            logger.error("Invalid JWT token - {}", ex.message)
        } catch (ex: ExpiredJwtException) {
            logger.error("Expired JWT token - {}", ex.message)
        } catch (ex: UnsupportedJwtException) {
            logger.error("Unsupported JWT token - {}", ex.message)
        } catch (ex: IllegalArgumentException) {
            logger.error("JWT claims string is empty - {}", ex.message)
        }
        return false
    }

}