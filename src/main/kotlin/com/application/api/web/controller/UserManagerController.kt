package com.application.api.web.controller

import com.application.api.web.entities.UserRequest
import com.application.api.web.entities.UserResponse
import com.application.api.web.entities.UsersResponse
import com.domain.entities.User

import com.domain.service.UserManagerService

import io.javalin.http.Context

import org.eclipse.jetty.http.HttpStatus

import org.slf4j.LoggerFactory

const val ID_PARAM = "id"

class UserManagerController(private val service: UserManagerService) {
    private val LOG = LoggerFactory.getLogger(UserManagerController::class.java)


    fun listUsers(): UsersResponse {
        return UsersResponse(service.listUsers())
    }

    fun create(ctx: Context) {
        ctx.bodyValidator<UserRequest>()
            .check({ it.name.isNotBlank() })
            .get().also { user ->
                service.createUser(user.userModel()).apply {
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

    fun getUserById(ctx: Context): UserResponse {
        val id = ctx.pathParam(ID_PARAM)
        return UserResponse(service.getUserById(id.toInt())).also {
            LOG.info("get info about the userID: ${it.id}")
        }
    }

    fun updateUser(ctx: Context) {
        val id = ctx.pathParam<Int>(ID_PARAM).get()
        ctx.bodyValidator<UserRequest>()
            .check({ it.name.isNotBlank() })
            .get().apply {
                val user = service.updateUser(id, this.userModel())
                ctx.json(UserResponse(user))
            }
    }
}