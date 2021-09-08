package com.application.config


import com.application.api.routes.RegisterUserManagerRouter
import com.application.api.web.advice.AdviceHandlerError
import io.javalin.Javalin
import io.prometheus.client.exporter.HTTPServer
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.handler.StatisticsHandler
import org.eclipse.jetty.util.thread.QueuedThreadPool
import org.flywaydb.core.Flyway
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.jetbrains.exposed.sql.Database

import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level


class Application : KoinComponent {
    private val route: RegisterUserManagerRouter by inject()
    private val environment: EnvironmentConfig by inject()

    private val statisticsHandler = StatisticsHandler()
    private val queuedThreadPool = QueuedThreadPool(200, 8, 60_000)

    fun startApplication(): Javalin {
        startKoin {
            printLogger(Level.DEBUG)
            modules(ModulesConfig.allModule)
        }

        runMigration()

        return registerAll().start()
    }

    private fun registerAll(): Javalin {
        val app = Javalin.create { config ->
            config.apply {
                contextPath = "/v1"
                addStaticFiles("/swagger")
                addSinglePageRoot("", "/swagger/swagger-ui.html")
                server {
                    //change this to get from config env

                    Server(queuedThreadPool).apply {
                        handler = statisticsHandler
                    }
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

        AdviceHandlerError.register(app)
        initializePrometheus()
        initializeDB(environment)

        return app
    }

    private fun initializePrometheus() {
        //TODO fix this later
        HTTPServer(7080)
    }

    private fun runMigration() {
        Flyway.configure().locations("db/migration")
            .dataSource("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres")
            .load().migrate()
    }

    private fun initializeDB(environmentConfig: EnvironmentConfig) {
        Database.connect(DataSourceConfig.getConfig(environmentConfig))
    }
}