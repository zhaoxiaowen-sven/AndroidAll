package com.sven.androidall.coroutine

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.alibaba.android.arouter.facade.annotation.Route
import com.sven.androidall.R
import com.sven.androidall.contants.RouteConstants
import com.sven.androidall.coroutine.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Route(path = RouteConstants.COROUTINE)
class CoroutineActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)
        val btn = findViewById<Button>(R.id.bt_coroutine)
        val respository = UserRepository()

        btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                MainScope().launch {
                    val user = withContext(Dispatchers.Default) {
                        respository.getUser("zxw")
                    }
                    btn.text = user.name
                }
            }
        })
    }
}