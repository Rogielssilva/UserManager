package com.application.api.web.controller

import com.model.User
import com.domain.service.UserManagerService

import io.javalin.http.Context
import org.eclipse.jetty.http.HttpStatus

const val ID_PARAM = "id"

class UserManagerController(private val service: UserManagerService) {

    fun listUsers(): List<User> {
        return service.listUsers()
    }

    fun create(ctx: Context) {
        //TODO handle exception
        ctx.bodyValidator<User>()
            .check({ it.name.isNotBlank() })
            .get().also { user ->
                service.createUser(user).apply {
                    ctx.status(HttpStatus.CREATED_201)
                    ctx.json(this)
                }
            }
    }

    fun delete(ctx: Context) {
        ctx.pathParam<Int>(ID_PARAM)
            .get().apply {
                service.deleteUser(this)
            }
    }

    fun getUserById(ctx: Context) {
        ctx.pathParam<Int>(ID_PARAM)
            .get().apply {
                service.getUserById(this).apply {
                    ctx.json(this)
                }

            }
    }

    fun updateUser(ctx: Context) {
        val id = ctx.pathParam<Int>(ID_PARAM).get()
        ctx.bodyValidator<User>()
            .check({ it.name.isNotBlank() })
            .get().apply {
                this.id = id
                service.updateUser(this)
                ctx.json(this)
            }
    }
}


