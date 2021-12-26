package com.sven.androidall

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test


fun main() {

//    test01()
//    testLaunch()

//    testCoroutineContext()

    // testCoroutineContext()

//    testCoroutineStart()

//    testCoroutineScope2()

    runBlocking {
//        testException()
        launch {
            flow {
                for (i in 1..3) {
                    delay(100)
                    emit(i)
                }
            }.collect { value -> println("value :${value}") }
        }

//        launch {
//            for (k in 1..3) {
//                println("I'm not blocked $k")
//                delay(100)
//            }
//        }
        // Collect the flow
//        simple().collect { value -> println(value) }
    }
}

private fun testLaunch() {
    val runBlockingJob = runBlocking {
        println("runblocking")
        41
    }
    println("runblocking = + $runBlockingJob")

    val launchJob = GlobalScope.launch {
        println("launch 启动一个协程")
    }
    println("launchJob = $launchJob")

    val asyncJob = GlobalScope.async {
        println("async 启动一个协程")
        "async 的返回值"
    }
    println("asyncJob = $asyncJob")
}

private fun testCoroutineContext() {
    val coroutineContext1 = Job() + CoroutineName("这是第一个上下文")
    println("coroutineContext1 $coroutineContext1")
    val coroutineContext2 = coroutineContext1 + Dispatchers.Default + CoroutineName("这是第二个上下文")
    println("coroutineContext2 $coroutineContext2")
    val coroutineContext3 = coroutineContext2 + Dispatchers.Main + CoroutineName("这是第三个上下文")
    println("coroutineContext3 $coroutineContext3")
}


fun testCoroutineScope2() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("exceptionHandler ${coroutineContext[CoroutineName]} $throwable")
    }
    GlobalScope.launch(Dispatchers.Main + CoroutineName("scope1") + exceptionHandler) {
        println("scope --------- 1")
        launch(CoroutineName("scope2") + exceptionHandler) {
            println("scope --------- 2")
            throw  NullPointerException("空指针")
            println("scope --------- 3")
        }
        val scope3 = launch(CoroutineName("scope3") + exceptionHandler) {
            println("scope --------- 4")
            delay(2000)
            println("scope --------- 5")
        }
        scope3.join()
        println("scope --------- 6")
    }
}


private fun testCoroutineStart() {
    val defaultJob = GlobalScope.launch {
        println("defaultJob CoroutineStart.DEFAULT")
    }
    defaultJob.cancel()
    val lazyJob = GlobalScope.launch(start = CoroutineStart.LAZY) {
        println("lazyJob CoroutineStart.LAZY")
    }
    val atomicJob = GlobalScope.launch(start = CoroutineStart.ATOMIC) {
        println("atomicJob CoroutineStart.ATOMIC挂起前")
        delay(100)
        println("atomicJob CoroutineStart.ATOMIC挂起后")
    }
    atomicJob.cancel()
    val undispatchedJob = GlobalScope.launch(start = CoroutineStart.UNDISPATCHED) {
        println("undispatchedJob CoroutineStart.UNDISPATCHED挂起前")
        delay(100)
        println("undispatchedJob CoroutineStart.UNDISPATCHED挂起后")
    }
    undispatchedJob.cancel()
}


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


fun testException(){
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("exceptionHandler ${coroutineContext[CoroutineName]} 处理异常 ：$throwable")
    }
    GlobalScope.launch(CoroutineName("父协程") + exceptionHandler){
        val job = launch(CoroutineName("子协程")) {
            println("${Thread.currentThread().name} 我要开始抛异常了" )
            for (index in 0..10){
                launch(CoroutineName("孙子协程$index")) {
                    println("${Thread.currentThread().name} ${coroutineContext[CoroutineName]}" )
                }
            }
            throw NullPointerException("空指针异常")
        }
        for (index in 0..10){
            launch(CoroutineName("子协程$index")) {
                println("${Thread.currentThread().name} ${coroutineContext[CoroutineName]}" )
            }
        }
        try {
            job.join()
        } catch (e: Exception) {
            println("exp $e")
        }
        println("${Thread.currentThread().name} end")
    }
}

fun test() {

}

fun simple(): Flow<Int> = flow { // flow builder
    for (i in 1..3) {
        delay(100) // pretend we are doing something useful here
        emit(i) // emit next value
    }
}