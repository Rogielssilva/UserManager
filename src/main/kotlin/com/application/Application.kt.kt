package com.application


import com.application.api.routes.RegisterRouter
import com.application.api.web.error.ErrorExceptionHandler
import com.application.config.ModulesConfig
import io.javalin.Javalin
import org.eclipse.jetty.server.Server
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level


class Application : KoinComponent {

    //TODO add in the route
    private val route: RegisterRouter by inject()

    fun startApplication(): Javalin {
        startKoin {
            printLogger(Level.DEBUG)
            modules(ModulesConfig.allModule)
        }

        val register = registerInit()

        return register.start()
    }


    private fun registerInit(): Javalin {
        //TODO

        val app = Javalin.create { javalinConfig ->
            javalinConfig.apply {
                contextPath = "/v1"
                addStaticFiles("/swagger")
                addSinglePageRoot("", "/swagger/swagger-ui.html")
                server {
                    Server(7000)
                }
            }
        }.events { event ->
            event.serverStopping {
                //should I get from context?
                stopKoin()
            }
        }

        app.routes {
            route.register()
        }

        ErrorExceptionHandler.register(app)

        return app
    }


}