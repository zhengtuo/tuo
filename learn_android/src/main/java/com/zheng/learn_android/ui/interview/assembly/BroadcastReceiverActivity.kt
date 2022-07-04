package com.zheng.learn_android.ui.interview.assembly

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.zheng.learn_android.R

class BroadcastReceiverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.la_activity_broadcast)
    }

    //静态注册网络变化广播 android8.0后无效
    fun staticRegisterBroadcast(view: View) {
        //val intent = Intent("android.net.conn.CONNECTIVITY_CHANGE")
        //sendBroadcast(intent)
    }

    //动态注册网络变化广播
    fun dynamicRegisterBroadcast(view: View) {
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        val receiver = NetReceiver()
        registerReceiver(receiver, intentFilter)
    }

    //发送自定义广播
    fun sendCustomizeBroadcast(view: View) {
        val intent = Intent("com.zheng.learn_android.CUSTOMIZE_BROADCAST")
        sendBroadcast(intent)
    }

    //发送有序广播
    fun sendOrderlyBroadcast(view: View) {
        val intent = Intent("com.zheng.learn_android.CUSTOMIZE_BROADCAST")
        sendBroadcast(intent)
    }

    //发送本地广播 本地广播只能动态注册
    fun sendLocalBroadcast(view: View) {
        val localBroadcastManager = LocalBroadcastManager.getInstance(this)
        //动态注册本地广播监听器
        val intentFilter = IntentFilter()
        intentFilter.addAction("com.zheng.learn_android.LOCAL_BROADCAST")
        val localReceiver = LocalReceiver()
        localBroadcastManager.registerReceiver(localReceiver, intentFilter)
        val intent = Intent("com.zheng.learn_android.LOCAL_BROADCAST")
        localBroadcastManager.sendBroadcast(intent)
    }

    //粘性广播
    fun sendStickyBroadcast(view: View) {
        val intent = Intent("com.zheng.learn_android.STICKY_BROADCAST")
        sendStickyBroadcast(intent)
    }

    //注册粘性广播
    fun registerStickyBroadcast(view: View) {
        val intentFilter = IntentFilter()
        intentFilter.addAction("com.zheng.learn_android.STICKY_BROADCAST")
        val receiver = StickReceiver()
        registerReceiver(receiver, intentFilter)
    }
}