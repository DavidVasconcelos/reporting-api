package com.speedyteller.reporting.api.domain.model

import com.speedyteller.reporting.api.domain.entity.CustomerEntity
import java.time.LocalDateTime

data class Customer(
    var id: Long? = null,
    var createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null,
    var deletedAt: LocalDateTime? = null,
    var number: String? = null,
    var expiryMonth: String? = null,
    var expiryYear: String? = null,
    var startMonth: String? = null,
    var startYear: String? = null,
    var issueNumber: String? = null,
    var email: String? = null,
    var birthday: LocalDateTime? = null,
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
) {
    constructor(entity: CustomerEntity) : this() {
        this.id = entity.id
        this.createdAt = entity.createdAt
        this.updatedAt = entity.updatedAt
        this.deletedAt = entity.deletedAt
        this.number = entity.number
        this.expiryMonth = entity.expiryMonth
        this.expiryYear = entity.expiryYear
        this.startMonth = entity.startMonth
        this.startYear = entity.startYear
        this.issueNumber = entity.issueNumber
        this.email = entity.email
        this.birthday = entity.birthday
        this.gender = entity.gender
        this.billingTitle = entity.billingTitle
        this.billingFirstName = entity.billingFirstName
        this.billingLastName = entity.billingLastName
        this.billingCompany = entity.billingCompany
        this.billingAddress1 = entity.billingAddress1
        this.billingAddress2 = entity.billingAddress2
        this.billingCity = entity.billingCity
        this.billingPostcode = entity.billingPostcode
        this.billingState = entity.billingState
        this.billingCountry = entity.billingCountry
        this.billingPhone = entity.billingPhone
        this.billingFax = entity.billingFax
        this.shippingTitle = entity.shippingTitle
        this.shippingFirstName = entity.shippingFirstName
        this.shippingLastName = entity.shippingLastName
        this.shippingCompany = entity.shippingCompany
        this.shippingAddress1 = entity.shippingAddress1
        this.shippingAddress2 = entity.shippingAddress2
        this.shippingCity = entity.shippingCity
        this.shippingPostcode = entity.shippingPostcode
        this.shippingState = entity.shippingState
        this.shippingCountry = entity.shippingCountry
        this.shippingPhone = entity.shippingPhone
        this.shippingFax = entity.shippingFax
    }
}
