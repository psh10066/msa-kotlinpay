package com.psh10066.common

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component

@Aspect
@Component
class LoggingAspect(
    val loggingProducer: LoggingProducer
) {

    @Before("execution(* com.psh10066.*.adapter.in.web.*.*(..))")
    fun beforeMethodExecution(joinPoint: JoinPoint) {
        val methodName = joinPoint.signature.name;
        loggingProducer.sendMessage("logging", "Before executing method: $methodName")
    }
}