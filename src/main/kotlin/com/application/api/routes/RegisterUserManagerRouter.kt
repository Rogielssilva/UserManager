package com.application.api.routes

import com.application.api.web.controller.UserManagerController
import com.application.api.web.entities.UsersResponse
import io.javalin.apibuilder.ApiBuilder.*
import io.ktor.features.*


class RegisterUserManagerRouter(private val userManagerController: UserManagerController) : Router {
    override fun register() {
        path("/users") {
            get { it.json(UsersResponse(userManagerController.listUsers())) }
            post { ctx -> userManagerController.create(ctx) }
            path("/:id") {
                delete { ctx -> userManagerController.delete(ctx) }
                put { ctx -> userManagerController.updateUser(ctx) }
                get { ctx -> userManagerController.getUserById(ctx) }
            }
        }

    }
}