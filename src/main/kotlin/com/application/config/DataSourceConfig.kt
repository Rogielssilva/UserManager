package com.application.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource


object DataSourceConfig {
    //TODO get from env
    fun getConfig(): HikariDataSource {


        val config = HikariConfig().apply {
            this.jdbcUrl = "jdbc:postgresql://localhost:5432/postgres"
            this.username = "postgres"
            this.maximumPoolSize = 10
            this.driverClassName = "org.postgresql.Driver"
            this.password = "postgres"

        }
        return HikariDataSource(config)
    }


}