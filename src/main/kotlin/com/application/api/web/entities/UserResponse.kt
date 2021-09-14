package com.application.api.web.entities

import com.domain.entities.User

class UserResponse(val id: Int, val name: String) {

    constructor (user: User) : this(
        id = user.id,
        name = user.name
    )
}

data class UsersResponse(val users: List<User>)
