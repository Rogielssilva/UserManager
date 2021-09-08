package com.application.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource


object DataSourceConfig {
    //TODO get from env
    fun getConfig(env: EnvironmentConfig): HikariDataSource {
        val config = HikariConfig().apply {
            this.jdbcUrl = env.urlJdbc
            this.username = env.username
            this.maximumPoolSize = env.pool
            this.driverClassName = env.driver
            this.password = env.password
        }

        return HikariDataSource(config)
    }


}