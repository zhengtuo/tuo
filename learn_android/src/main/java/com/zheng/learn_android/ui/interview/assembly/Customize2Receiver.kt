package com.zheng.learn_android.ui.interview.assembly

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

class Customize2Receiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        Toast.makeText(context,"Customize2Receiver 已收到自定义广播",Toast.LENGTH_SHORT).show()
    }
}