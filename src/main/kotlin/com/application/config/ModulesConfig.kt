package com.application.config

import com.application.api.routes.RegisterRouter
import com.application.api.web.controller.UserManagerController
import com.domain.repository.UserManagerRepository
import com.domain.repository.UserManagerRepositoryImpl
import com.domain.service.UserManagerService
import org.koin.dsl.module

object ModulesConfig {
    private val userManagerModule = module {
        single { UserManagerRepositoryImpl() as UserManagerRepository }
        factory { UserManagerService(get()) }
        single { UserManagerController(get()) }
    }

    private val configModule = module {
        single { RegisterRouter(get()) }
    }

    // TODO add here the any new module injection
    internal val allModule = listOf(userManagerModule, configModule)
}