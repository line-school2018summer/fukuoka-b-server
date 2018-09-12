package com.line.fukuokabserver.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

data class ErrorResponse(val msg: String)

class UnauthorizedException(val msg: String): Exception(msg)

@ControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(UnauthorizedException::class)
    fun unauthorized(req: HttpServletRequest, e: UnauthorizedException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse(e.msg), HttpStatus.UNAUTHORIZED)
    }
}