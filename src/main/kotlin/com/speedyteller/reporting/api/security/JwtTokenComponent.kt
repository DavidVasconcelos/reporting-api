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
    @Value("\${security.jwt-expiration-time}") val jwtExpirationTime: Int,
) {
    private val secretKey by lazy {
        Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
    }

    fun generateAccessToken(user: User): String {
        val roles = user.authorities.map { it.authority }
        return Jwts.builder()
            .subject(user.username)
            .claim("roles", roles)
            .issuer(issuer)
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + jwtExpirationTime * SECOND_MILLIS))
            .signWith(secretKey)
            .compact()
    }

    fun getUsername(token: String): String = getClaims(token)["sub"].toString()

    @Suppress("UNCHECKED_CAST")
    fun getRoles(token: String): List<String> {
        val claims = getClaims(token)
        return claims["roles"] as? List<String> ?: emptyList()
    }

    private fun getClaims(token: String): Claims {
        val sanitizedToken = sanitizeToken(token)
        return Jwts.parser().verifyWith(secretKey)
            .build()
            .parseSignedClaims(sanitizedToken)
            .payload
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

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(this::class.java)
        const val SECOND_MILLIS = 1000
    }
}
