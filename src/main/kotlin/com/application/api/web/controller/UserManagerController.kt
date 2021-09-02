package com.application.api.web.controller

import com.model.User
import com.service.UserManagerService
import io.javalin.http.Context


class UserManagerController(private val service: UserManagerService) {
    fun listUsers(): List<User> {
        return service.listUsers()
    }
    fun create(ctx: Context): User{

        val user: User = ctx.bodyAsClass(User::class.java)


        //TODO validation
        return service.createUser(user)

    }
}