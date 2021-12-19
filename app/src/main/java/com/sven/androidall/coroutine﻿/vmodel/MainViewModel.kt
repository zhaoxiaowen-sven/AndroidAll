package com.sven.androidall.coroutine

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sven.androidall.coroutine.bean.User
import com.sven.androidall.coroutine.repository.UserRepository
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {
    val userLiveData = MutableLiveData<User>()

//    fun getUser(name:String):User {
////         viewModelScope.launch {
////             respository.getUser(name)
////        }
//        return null
//    }
}