package com.psh10066.common;

import org.springframework.context.annotation.Configuration
import java.util.concurrent.CountDownLatch

@Configuration
class CountDownLatchManager {
    private val countDownLatchMap: MutableMap<String, CountDownLatch> = HashMap()
    private val stringMap: MutableMap<String, String> = HashMap()

    fun addCountDownLatch(key: String) {
        countDownLatchMap[key] = CountDownLatch(1)
    }

    fun setDataForKey(key: String, data: String) {
        stringMap[key] = data
    }

    fun getDataForKey(key: String): String? {
        return stringMap[key]
    }

    fun getCountDownLatch(key: String): CountDownLatch? {
        return countDownLatchMap[key]
    }
}