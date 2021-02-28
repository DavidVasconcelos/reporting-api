package com.speedyteller.reporting.api.validator

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class ValidStatusValidator : ConstraintValidator<ValidStatus, String> {

    private lateinit var annotation: ValidStatus

    override fun initialize(annotation: ValidStatus) {
        this.annotation = annotation
    }

    override fun isValid(valueForValidation: String?, context: ConstraintValidatorContext?): Boolean {

        return valueForValidation?.let { value ->
            this.annotation.enumClass.java.enumConstants.any { it.name == value.toUpperCase() }
        } ?: true
    }
}
