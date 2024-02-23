package com.psh10066.common.exception

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    private val log = LoggerFactory.getLogger(this.javaClass)!!

    /**
     * 잘못된 요청
     * HttpStatus 400
     */
    @ExceptionHandler(CustomException::class)
    fun customException(e: CustomException): ResponseEntity<ErrorResponse> {
        log.info("error : ", e)
        return ErrorResponse.toEntity(e.errorType)
    }

    /**
     * 허용되지 않는 방법
     * HttpStatus 405
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun httpRequestMethodNotSupportedException(e: HttpRequestMethodNotSupportedException?): ResponseEntity<ErrorResponse> {
        log.info("error : ", e)
        return ErrorResponse.toEntity(ErrorType.METHOD_NOT_ALLOWED)
    }

    /**
     * Validation 실패 (@RequestBody Json Parsing 실패)
     * HttpStatus 400
     */
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun httpMessageNotReadableException(e: Exception): ResponseEntity<ErrorResponse> {
        log.info("error : ", e)
        return ErrorResponse.toEntity(ErrorType.BAD_REQUEST, e.message!!)
    }

    /**
     * Validation 실패 (@RequestBody, @ModelAttribute @Valid 유효성 검사 실패)
     * HttpStatus 400
     */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        log.info("error : ", e)
        // @Valid 어노테이션의 message 속성으로 return
        return ErrorResponse.toEntity(ErrorType.BAD_REQUEST, e.getFieldError()?.getDefaultMessage()!!)
    }

    /**
     * 알 수 없는 오류(내부 서버 오류)
     * httpStatus 500
     */
    @ExceptionHandler(Exception::class)
    fun exception(e: Exception): ResponseEntity<ErrorResponse> {
        log.error("error : $e")
        log.info("error : ", e)
        return ErrorResponse.toEntity(ErrorType.INTERNAL_SERVER_ERROR)
    }
}