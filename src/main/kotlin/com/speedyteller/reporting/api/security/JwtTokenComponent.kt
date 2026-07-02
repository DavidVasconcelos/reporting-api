package com.speedyteller.reporting.api.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.IncorrectClaimException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.Date

@Component
class JwtTokenComponent(
    @Value("\${jwt.secret}") val secret: String,
    @Value("\${jwt.issuer}") val issuer: String,
    @Value("\${caching.spring.loginListTTL}") val jwtExpirationTime: Int,
) {
    private var logger: Logger = LoggerFactory.getLogger(this::class.java)

    private val secretKey by lazy {
        Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
    }

    fun generateAccessToken(user: User): String = Jwts.builder()
        .subject(user.username)
        .issuer(issuer)
        .issuedAt(Date())
        .expiration(Date(System.currentTimeMillis() + jwtExpirationTime))
        .signWith(secretKey)
        .compact()

    fun getUsername(token: String): String {
        val sanitizedToken = sanitizeToken(token)
        val claims: Claims = Jwts.parser().verifyWith(secretKey)
            .build()
            .parseSignedClaims(sanitizedToken)
            .payload
        return claims["sub"].toString()
    }

    fun validate(token: String): Boolean {
        val sanitizedToken = sanitizeToken(token)
        try {
            Jwts.parser()
                .requireIssuer(issuer)
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(sanitizedToken)
            return true
        } catch (ex: IncorrectClaimException) {
            logger.error("Invalid JWT claim (e.g., wrong issuer) - {}", ex.message)
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

    private fun sanitizeToken(token: String): String = token.removePrefix("Bearer").trim()
}
