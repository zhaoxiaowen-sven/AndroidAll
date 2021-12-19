package com.sven.androidall

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jetbrains.annotations.TestOnly

class CoroutineTest01 {


    @TestOnly
    fun test01() = runBlocking {
        val job01 = GlobalScope.launch {
            println("job01")
        }

        val job02 = async {
            println("job02")
            "job02 ret"
        }

        println(job02.await())
    }
}