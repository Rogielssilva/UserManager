package com.application.api.web.error

import io.javalin.Javalin
import io.ktor.features.*
import org.eclipse.jetty.http.HttpStatus
import org.slf4j.LoggerFactory

private data class ErrorResponse(val message: String, val status: Int)

object AdviceHandlerError {
    private val LOG = LoggerFactory.getLogger(AdviceHandlerError::class.java)
    fun register(app: Javalin) {
        app.exception(Exception::class.java) { e, ctx ->
            LOG.error("got a exception in the request ${ctx.url()}", e)
            val error = ErrorResponse(e.message.toString(), HttpStatus.INTERNAL_SERVER_ERROR_500)
            ctx.json(error).status(HttpStatus.INTERNAL_SERVER_ERROR_500)
        }
        app.exception(NotFoundException::class.java) { exception, ctx ->
            LOG.error("got the NotFoundException in the request of ${ctx.url()}", exception)
            val error = ErrorResponse(exception.message.toString(), HttpStatus.NOT_FOUND_404)
            ctx.json(error).status(HttpStatus.NOT_FOUND_404)
        }
    }
}