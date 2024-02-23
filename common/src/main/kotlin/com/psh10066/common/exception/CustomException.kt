package com.psh10066.common.exception

class CustomException(val errorType: ErrorType) : RuntimeException(errorType.message)