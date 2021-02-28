package com.speedyteller.reporting.api.validator

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class ValidOperationValidator: ConstraintValidator<ValidOperation, String> {

    private lateinit var annotation: ValidOperation

    override fun initialize(annotation: ValidOperation) {
        this.annotation = annotation
    }

    override fun isValid(valueForValidation: String?, context: ConstraintValidatorContext?): Boolean {

        return valueForValidation?.let { value ->
            this.annotation.enumClass.java.enumConstants.any { it.name == value.toUpperCase() }
        } ?: true
    }
}
