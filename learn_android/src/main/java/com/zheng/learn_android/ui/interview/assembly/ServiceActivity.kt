package com.zheng.learn_android.ui.interview.assembly

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.zheng.learn_android.R
import com.zheng.learn_android.ui.interview.LearnBinder
import com.zheng.learn_android.ui.interview.LearnService

class ServiceActivity : AppCompatActivity() {

    val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            if (p1 != null) {
                val binder: LearnBinder = p1 as LearnBinder
                binder.startRun()
            }

        }

        override fun onServiceDisconnected(p0: ComponentName?) {

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.la_activity_service)
    }

    //开启一个Service
    fun startLearnService(view: View) {
        val intent = Intent(this, LearnService::class.java)
        startService(intent)
    }

    //关闭Service
    fun closeLearnService(view: View) {
        val intent = Intent(this, LearnService::class.java)
        stopService(intent)
    }

    //绑定Service
    fun bindLearnService(view: View) {
        val intent = Intent(this, LearnService::class.java)
        bindService(intent, serviceConnection, BIND_AUTO_CREATE)
    }

    //解绑Service
    fun unBindLearnService(view: View) {
        unbindService(serviceConnection)
    }

}