package com.zheng.learn_android.ui.interview

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import timber.log.Timber

class LearnService : Service() {

    val binder = LearnBinder()

    override fun onCreate() {
        super.onCreate()
        Timber.tag("LearnService").d("onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Timber.tag("LearnService").d("onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder {
        Timber.tag("LearnService").d("onBind")
        return binder
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.tag("LearnService").d("onDestroy")
    }

}
class LearnBinder: Binder() {
    fun startRun() {
        Timber.tag("LearnService").d("startRun")
    }
}