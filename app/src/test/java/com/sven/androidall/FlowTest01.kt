package com.sven.androidall

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import org.junit.Test

class FlowTest01 {
    @Test
    fun testAll() {
        runBlocking {

            test()
        }
    }

    @Test
    fun test() {
        GlobalScope.launch {
            flow {
                for (i in 1..3) {
                    delay(100)
                    emit(i)
                }
            }.collect { value -> println("value :${value}") }
        }
    }
}