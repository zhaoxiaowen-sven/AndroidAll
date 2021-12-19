package com.sven.androidall.coroutine.repository

import com.sven.androidall.coroutine.bean.User
import kotlinx.coroutines.delay

class UserRepository {

    suspend fun getUser(name: String) : User {
        delay(1000)
        return User("zxw", 10)
    }
}