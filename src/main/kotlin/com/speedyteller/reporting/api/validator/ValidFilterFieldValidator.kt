package com.speedyteller.reporting.api.validator

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class ValidFilterFieldValidator : ConstraintValidator<ValidFilterField, String> {

    private lateinit var annotation: ValidFilterField

    override fun initialize(annotation: ValidFilterField) {
        this.annotation = annotation
    }

    override fun isValid(valueForValidation: String?, context: ConstraintValidatorContext?): Boolean {

        return valueForValidation?.let { value ->
            this.annotation.enumClass.java.enumConstants.any { it.filterField == value.trim() }
        } ?: true
    }
}
