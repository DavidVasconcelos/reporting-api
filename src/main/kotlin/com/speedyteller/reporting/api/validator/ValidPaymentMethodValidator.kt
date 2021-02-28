package com.speedyteller.reporting.api.validator

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class ValidPaymentMethodValidator: ConstraintValidator<ValidPaymentMethod, String> {

    private lateinit var annotation: ValidPaymentMethod

    override fun initialize(annotation: ValidPaymentMethod) {
        this.annotation = annotation
    }

    override fun isValid(valueForValidation: String?, context: ConstraintValidatorContext?): Boolean {

        return valueForValidation?.let { value ->
            this.annotation.enumClass.java.enumConstants.any { it.name == value.toUpperCase() }
        } ?: true
    }
}
