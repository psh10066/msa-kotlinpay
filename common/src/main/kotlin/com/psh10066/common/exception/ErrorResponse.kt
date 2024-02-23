package com.psh10066.common.exception

import org.springframework.http.ResponseEntity

class ErrorResponse(
    val rt: Int,
    val rtMsg: String
) {

    companion object {
        fun toEntity(errorType: ErrorType): ResponseEntity<ErrorResponse> {
            return ResponseEntity
                .status(errorType.httpStatus)
                .body(ErrorResponse(errorType.code, errorType.message))
        }

        fun toEntity(errorType: ErrorType, rtMsg: String): ResponseEntity<ErrorResponse> {
            return ResponseEntity
                .status(errorType.httpStatus)
                .body(ErrorResponse(errorType.code, rtMsg))
        }

        fun of(errorType: ErrorType): ErrorResponse {
            return ErrorResponse(errorType.code, errorType.message)
        }
    }
}