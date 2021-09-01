package com.api.web

import com.model.User
import com.service.UserManagerService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import io.swagger.core.filter.AbstractSpecFilter
import io.swagger.v3.oas.annotations.OpenAPIDefinition


class UserManagerController : KoinComponent {
    private val service: UserManagerService by inject()
    fun listUsers(): List<User> {
        return service.listUsers()
    }

    
}