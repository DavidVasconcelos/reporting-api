package com.speedyteller.reporting.api.validator

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class ValidErrorCodeValidator : ConstraintValidator<ValidErrorCode, String> {

    private lateinit var annotation: ValidErrorCode

    override fun initialize(annotation: ValidErrorCode) {
        this.annotation = annotation
    }

    override fun isValid(valueForValidation: String?, context: ConstraintValidatorContext?): Boolean {

        return valueForValidation?.let { value ->
            this.annotation.enumClass.java.enumConstants.any { it.errorCode == value.trim() }
        } ?: true
    }
}
