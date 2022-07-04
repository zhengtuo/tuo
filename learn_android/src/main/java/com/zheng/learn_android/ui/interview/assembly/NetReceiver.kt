package com.zheng.learn_android.ui.interview.assembly

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

class NetReceiver : BroadcastReceiver() {

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        val connectivityManager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.apply {
            getNetworkInfo(ConnectivityManager.TYPE_WIFI)?.apply {
                if (isConnected) {
                    Toast.makeText(context,"wifi已连接",Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context,"wifi已断开",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}