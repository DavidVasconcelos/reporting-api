package com.speedyteller.reporting.api.domain.dto

import com.speedyteller.reporting.api.domain.model.Customer
import com.speedyteller.reporting.api.extension.toStringPattern

data class CustomerDTO(

    var id: Long? = null,
    var created_at: String? = null,
    var updated_at: String? = null,
    var deleted_at: String? = null,
    var number: String? = null,
    var expiryMonth: String? = null,
    var expiryYear: String? = null,
    var startMonth: String? = null,
    var startYear: String? = null,
    var issueNumber: String? = null,
    var email: String? = null,
    var birthday: String? = null,
    var gender: String? = null,
    var billingTitle: String? = null,
    var billingFirstName: String? = null,
    var billingLastName: String? = null,
    var billingCompany: String? = null,
    var billingAddress1: String? = null,
    var billingAddress2: String? = null,
    var billingCity: String? = null,
    var billingPostcode: String? = null,
    var billingState: String? = null,
    var billingCountry: String? = null,
    var billingPhone: String? = null,
    var billingFax: String? = null,
    var shippingTitle: String? = null,
    var shippingFirstName: String? = null,
    var shippingLastName: String? = null,
    var shippingCompany: String? = null,
    var shippingAddress1: String? = null,
    var shippingAddress2: String? = null,
    var shippingCity: String? = null,
    var shippingPostcode: String? = null,
    var shippingState: String? = null,
    var shippingCountry: String? = null,
    var shippingPhone: String? = null,
    var shippingFax: String? = null
){
    constructor(model: Customer) : this() {
        this.id = model.id
        this.created_at = model.created_at?.toStringPattern()
        this.updated_at = model.updated_at?.toStringPattern()
        this.deleted_at = model.deleted_at?.toStringPattern()
        this.number = model.number
        this.expiryMonth = model.expiryMonth
        this.expiryYear = model.expiryYear
        this.startMonth = model.startMonth
        this.startYear = model.startYear
        this.issueNumber = model.issueNumber
        this.email = model.email
        this.birthday = model.birthday?.toStringPattern()
        this.gender = model.gender
        this.billingTitle = model.billingTitle
        this.billingFirstName = model.billingFirstName
        this.billingLastName = model.billingLastName
        this.billingCompany = model.billingCompany
        this.billingAddress1 = model.billingAddress1
        this.billingAddress2 = model.billingAddress2
        this.billingCity = model.billingCity
        this.billingPostcode = model.billingPostcode
        this.billingState = model.billingState
        this.billingCountry = model.billingCountry
        this.billingPhone = model.billingPhone
        this.billingFax = model.billingFax
        this.shippingTitle = model.shippingTitle
        this.shippingFirstName = model.shippingFirstName
        this.shippingLastName = model.shippingLastName
        this.shippingCompany = model.shippingCompany
        this.shippingAddress1 = model.shippingAddress1
        this.shippingAddress2 = model.shippingAddress2
        this.shippingCity = model.shippingCity
        this.shippingPostcode = model.shippingPostcode
        this.shippingState = model.shippingState
        this.shippingCountry = model.shippingCountry
        this.shippingPhone = model.shippingPhone
        this.shippingFax = model.shippingFax
    }
}
