package com.speedyteller.reporting.api.domain.entity

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "customer")
data class CustomerEntity(
    @Id
    @Column(name = "id")
    val id: Long? = null,
    @Column(name = "created_at")
    val createdAt: LocalDateTime? = null,
    @Column(name = "updated_at")
    val updatedAt: LocalDateTime? = null,
    @Column(name = "deleted_at")
    val deletedAt: LocalDateTime? = null,
    @Column(name = "number")
    val number: String? = null,
    @Column(name = "expiry_month")
    val expiryMonth: String? = null,
    @Column(name = "expiry_year")
    val expiryYear: String? = null,
    @Column(name = "start_month")
    val startMonth: String? = null,
    @Column(name = "start_year")
    val startYear: String? = null,
    @Column(name = "issue_number")
    val issueNumber: String? = null,
    @Column(name = "email")
    val email: String? = null,
    @Column(name = "birthday")
    val birthday: LocalDateTime? = null,
    @Column(name = "gender")
    val gender: String? = null,
    @Column(name = "billing_title")
    val billingTitle: String? = null,
    @Column(name = "billing_first_name")
    val billingFirstName: String? = null,
    @Column(name = "billing_last_name")
    val billingLastName: String? = null,
    @Column(name = "billing_company")
    val billingCompany: String? = null,
    @Column(name = "billing_address_1")
    val billingAddress1: String? = null,
    @Column(name = "billing_address_2")
    val billingAddress2: String? = null,
    @Column(name = "billing_city")
    val billingCity: String? = null,
    @Column(name = "billing_postcode")
    val billingPostcode: String? = null,
    @Column(name = "billing_state")
    val billingState: String? = null,
    @Column(name = "billing_country")
    val billingCountry: String? = null,
    @Column(name = "billing_phone")
    val billingPhone: String? = null,
    @Column(name = "billing_fax")
    val billingFax: String? = null,
    @Column(name = "shipping_title")
    val shippingTitle: String? = null,
    @Column(name = "shipping_first_name")
    val shippingFirstName: String? = null,
    @Column(name = "shipping_last_name")
    val shippingLastName: String? = null,
    @Column(name = "shipping_company")
    val shippingCompany: String? = null,
    @Column(name = "shipping_address_1")
    val shippingAddress1: String? = null,
    @Column(name = "shipping_address_2")
    val shippingAddress2: String? = null,
    @Column(name = "shipping_city")
    val shippingCity: String? = null,
    @Column(name = "shipping_post_code")
    val shippingPostcode: String? = null,
    @Column(name = "shipping_state")
    val shippingState: String? = null,
    @Column(name = "shipping_country")
    val shippingCountry: String? = null,
    @Column(name = "shipping_phone")
    val shippingPhone: String? = null,
    @Column(name = "shipping_fax")
    val shippingFax: String? = null
)
