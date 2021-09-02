package com.example

import com.application.Application
import com.repository.UserManagerRepository
import com.repository.UserManagerRepositoryImpl
import com.service.UserManagerService
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

fun main() {
    Application().startApplication()
}

