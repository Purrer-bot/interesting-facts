package org.purrer.facts.controller

import org.purrer.facts.exception.ValidationException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


@org.springframework.web.bind.annotation.ControllerAdvice
class ControllerAdvice: ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [ValidationException::class])
    protected fun handleValidation(ex: RuntimeException?, request: WebRequest?):ResponseEntity<Any?>?{
        return handleExceptionInternal(ex!!, ex.message, HttpHeaders(), HttpStatus.BAD_REQUEST, request!!)
    }
    @ExceptionHandler(value = [RuntimeException::class])
    protected fun handleRuntime(ex: RuntimeException?, request: WebRequest?):ResponseEntity<Any?>?{
        return handleExceptionInternal(ex!!, ex.message, HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request!!)
    }

    @ExceptionHandler(value = [Exception::class])
    protected fun handleException(ex: RuntimeException?, request: WebRequest?):ResponseEntity<Any?>?{
        return handleExceptionInternal(ex!!, ex.message, HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request!!)
    }
}