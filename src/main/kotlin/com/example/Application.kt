package com.example

import com.api.web.UserManagerController
import com.repository.UserManagerRepository
import com.repository.UserManagerRepositoryImpl
import com.service.UserManagerService
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module


val userModule = module {
    single { UserManagerRepositoryImpl() as UserManagerRepository }
    factory { UserManagerService(get()) }
    //single { UserManagerController(get()) }
}

fun main() {
//    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
//        configureRouting()
//    }.start(wait = true)

    openapi


    startKoin {
        printLogger(Level.DEBUG)
        modules(userModule)
    }

    // TODO change to start application
    println(UserManagerController().listUsers())


}
