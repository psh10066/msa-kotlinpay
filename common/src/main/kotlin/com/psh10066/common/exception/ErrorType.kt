package com.psh10066.common.exception

import org.springframework.http.HttpStatus

enum class ErrorType(
    val httpStatus: HttpStatus,
    val code: Int,
    val message: String
) {
    NOT_FOUND(HttpStatus.NOT_FOUND, -1, HttpStatus.NOT_FOUND.getReasonPhrase()),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, -2, HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase()),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, -3, "잘못된 요청입니다."),

    UNAUTHORIZED_MEMBER(HttpStatus.UNAUTHORIZED, -701, "인증되지 않은 회원입니다."),
    NOT_VALID_ACCOUNT(HttpStatus.BAD_REQUEST, -702, "등록 가능한 계좌 정보가 아닙니다."),
    LACK_ACCOUNT_BALANCE(HttpStatus.BAD_REQUEST, -703, "계좌에 잔액이 부족합니다."),

    FORBIDDEN(HttpStatus.FORBIDDEN, -9000, HttpStatus.FORBIDDEN.getReasonPhrase()),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, -9999, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
}