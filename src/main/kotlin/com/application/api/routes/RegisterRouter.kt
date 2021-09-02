package com.application.api.routes

import com.application.api.web.controller.UserManagerController
import com.application.api.web.entities.UsersResponse
import io.javalin.apibuilder.ApiBuilder.*


class RegisterRouter(private val userManagerController: UserManagerController) {
    //TODO pass javalin as parameter when start to user swagger
    fun register() {
        path("/users") {
            get { it.json(UsersResponse(userManagerController.listUsers())) }

            post { ctx -> ctx.json(userManagerController.create(ctx)) }

        }
    }
}