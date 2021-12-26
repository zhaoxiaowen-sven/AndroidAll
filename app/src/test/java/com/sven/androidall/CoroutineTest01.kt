package com.sven.androidall

import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CoroutineTest01 {

    val scope : CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)


    @Test
    fun testLaunch() {
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

    val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
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

    @Test
     fun testCoroutineScope3() {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("exceptionHandler ${coroutineContext[CoroutineName]} $throwable")
        }
        GlobalScope.launch(Dispatchers.Main + CoroutineName("scope1") + exceptionHandler) {
            supervisorScope {
                println("scope --------- 1")
                launch(CoroutineName("scope2")) {
                    println("scope --------- 2")
                    throw  NullPointerException("空指针")
//                    println("scope --------- 3")
//                    val scope3 = launch(CoroutineName("scope3")) {
//                        println("scope --------- 4")
//                        delay(2000)
//                        println("scope --------- 5")
//                    }
//                    scope3.join()
                }
                val scope4 = launch(CoroutineName("scope4")) {
                    println("scope --------- 6")
//                    delay(2000)
                    println("scope --------- 7")
                }
                scope4.join()
                println("scope --------- 8")
            }
        }
    }
    @Test
     fun testCoroutineScope4() {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("exceptionHandler ${coroutineContext[CoroutineName]} $throwable")
        }
        val coroutineScope = CoroutineScope(SupervisorJob() +CoroutineName("coroutineScope"))
        GlobalScope.launch(Dispatchers.Main + CoroutineName("scope1") + exceptionHandler) {
           with(coroutineScope) {
                val scope2 = launch(CoroutineName("scope2") + exceptionHandler) {
                    println("scope 1--------- ${coroutineContext[CoroutineName]}")
                    throw  NullPointerException("空指针")
                }
                val scope3 = launch(CoroutineName("scope3") + exceptionHandler) {
                    scope2.join()
                    println("scope 2--------- ${coroutineContext[CoroutineName]}")
                    delay(2000)
                    println("scope 3--------- ${coroutineContext[CoroutineName]}")
                }
                scope2.join()
                println("scope 4--------- ${coroutineContext[CoroutineName]}")
                coroutineScope.cancel()
                scope3.join()
                println("scope 5--------- ${coroutineContext[CoroutineName]}")
            }
            println("scope 6--------- ${coroutineContext[CoroutineName]}")
        }
    }

    @Test
     fun testException() {
        runBlocking {
            println("测试 111")
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
                    println("我是异常 1111")
                    println("exp ${e.message}")
                }
                println("${Thread.currentThread().name} end")
            }
        }

    }

}