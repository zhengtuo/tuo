package com.zheng.learn_android.ui.interview.assembly

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

class StickReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        Toast.makeText(context,"StickReceiver 已收到粘性广播",Toast.LENGTH_SHORT).show()
    }
}