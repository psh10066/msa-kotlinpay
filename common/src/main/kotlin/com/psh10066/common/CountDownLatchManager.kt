package com.psh10066.common;

import org.springframework.context.annotation.Configuration
import java.util.concurrent.CountDownLatch

@Configuration
class CountDownLatchManager {
    private val countDownLatchMap: MutableMap<String, CountDownLatch> = HashMap()
    private val dataMap: MutableMap<String, Any> = HashMap()

    fun addCountDownLatch(key: String) {
        countDownLatchMap[key] = CountDownLatch(1)
    }

    fun setDataForKey(key: String, data: Any) {
        dataMap[key] = data
    }

    fun getDataForKey(key: String): Any? {
        return dataMap[key]
    }

    fun getCountDownLatch(key: String): CountDownLatch? {
        return countDownLatchMap[key]
    }
}