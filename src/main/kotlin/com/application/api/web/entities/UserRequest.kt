package com.application.api.web.entities

import com.domain.entities.User

data class UserRequest(val name: String) {
    fun userModel(): User {
        return User(name = this.name)
    }
}


