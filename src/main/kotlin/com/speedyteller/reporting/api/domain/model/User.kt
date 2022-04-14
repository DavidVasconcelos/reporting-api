package com.speedyteller.reporting.api.domain.model

import com.speedyteller.reporting.api.domain.entity.UserEntity

data class User(
    var id: Long? = null,
    var email: String? = null,
    var password: String? = null
) {
    constructor(entity: UserEntity) : this() {
        this.id = entity.id
        this.email = entity.email
        this.password = entity.password
    }
}
