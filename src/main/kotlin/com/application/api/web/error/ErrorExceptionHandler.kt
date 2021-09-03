package com.application.api.web.error

import io.javalin.Javalin
import org.eclipse.jetty.http.HttpStatus
import org.slf4j.LoggerFactory

private data class ErrorResponse(val errors: Map<String, List<String?>>)

object ErrorExceptionHandler {
    private val LOG = LoggerFactory.getLogger(ErrorExceptionHandler::class.java)

    fun register(app: Javalin) {
        app.exception(Exception::class.java) { e, ctx ->
            LOG.error("Exception occurred for req -> ${ctx.url()}", e)
            val error = ErrorResponse(mapOf("Unknow Error" to listOf(e.message ?: "Error occurred!")))
            ctx.json(error).status(HttpStatus.INTERNAL_SERVER_ERROR_500)
        }

    }


}