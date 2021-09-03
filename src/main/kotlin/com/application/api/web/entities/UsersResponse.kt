package com.application.api.web.entities

import com.model.User


data class UsersResponse(val users: List<User>)

data class UserRequest(val user: User)