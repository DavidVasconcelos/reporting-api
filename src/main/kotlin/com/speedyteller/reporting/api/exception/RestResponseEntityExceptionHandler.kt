package com.speedyteller.reporting.api.exception

import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class RestResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(Exception::class)
    fun handleAllExceptions(ex: Exception): ResponseEntity<Any> {
        logger.error(ex.message, ex)
        val errors = arrayListOf(
            Error(
                codigo = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                mensagem = ex.localizedMessage,
            ),
        )
        val errorResponse = ErrorResponse(INTERNAL_SERVER_ERROR_MESSAGE, errors)
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(ex: ConstraintViolationException): ResponseEntity<Any> {
        logger.warn(ex.message, ex)
        val errors = arrayListOf(
            Error(
                codigo = HttpStatus.BAD_REQUEST.value(),
                mensagem = ex.localizedMessage.run { this.substringAfter(COLON).trimStart() },
            ),
        )
        val errorResponse = ErrorResponse(VALIDATION_FAILED_MESSAGE, errors)
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: NotFoundException): ResponseEntity<Any> {
        logger.warn(ex.message, ex)
        val errors = arrayListOf(
            Error(
                codigo = HttpStatus.NOT_FOUND.value(),
                mensagem = ex.localizedMessage,
            ),
        )
        val errorResponse = ErrorResponse(NOT_FOUND_FAIL_MESSAGE, errors)
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(BadCredentialsException::class)
    fun handleBadCredentialsException(ex: BadCredentialsException): ResponseEntity<Any> {
        logger.warn(ex.message, ex)
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
    }

    @ExceptionHandler(BusinessValidationException::class)
    fun handleBusinessValidationException(ex: BusinessValidationException): ResponseEntity<Any> {
        logger.warn(ex.message, ex)
        val errors = arrayListOf(
            Error(
                codigo = HttpStatus.BAD_REQUEST.value(),
                mensagem = ex.localizedMessage,
            ),
        )
        val errorResponse = ErrorResponse(VALIDATION_FAILED_MESSAGE, errors)
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    override fun handleMissingServletRequestParameter(
        ex: MissingServletRequestParameterException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest,
    ): ResponseEntity<Any> {
        val errorMessage = "Required query parameter '${ex.parameterName}' is missing."
        val errors = arrayListOf(
            Error(
                codigo = HttpStatus.BAD_REQUEST.value(),
                mensagem = errorMessage,
            ),
        )
        val errorResponse = ErrorResponse(VALIDATION_FAILED_MESSAGE, errors)
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    companion object {
        const val NOT_FOUND_FAIL_MESSAGE = "NotFound"
        const val INTERNAL_SERVER_ERROR_MESSAGE = "Internal Server Error"
        const val VALIDATION_FAILED_MESSAGE = "Validation failed"
        const val COLON = ":"
    }
}
