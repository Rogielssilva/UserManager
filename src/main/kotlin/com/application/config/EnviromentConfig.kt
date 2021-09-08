package com.application.config

import com.natpryce.konfig.*


class EnvironmentConfig(config: Configuration = EnvironmentVariables()) {
    val urlJdbc = config[DATABASE_JDBC_URL]
    val username = config[DATABASE_USER_NAME]
    val password = config[DATABASE_PASSWORD]
    val driver = config[DATABASE_DRIVER]
    val pool = config[POOL]

    companion object {
        private val DATABASE_JDBC_URL by stringType
        private val DATABASE_USER_NAME by stringType
        private val DATABASE_PASSWORD by stringType
        private val DATABASE_DRIVER by stringType
        private val POOL by intType
    }
}