package com.zt.server

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.widget.Toast
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationManagerCompat

class DreloveyService:Service() {

    private var handler = Handler(Looper.getMainLooper())

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val channel = NotificationChannelCompat.Builder(
                "dreloveyService",
                NotificationManagerCompat.IMPORTANCE_DEFAULT
            ).setName("dreloveyServiceChannel")
                .build()
            val notification = Notification.Builder(this,channel.id)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("Aidl Drelovey Service")
                .build()
            NotificationManagerCompat.from(this).createNotificationChannel(channel)
            startForeground(1,notification)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        println("DreloveyService onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    private var myBinder = object:Drelovey.Stub(){
        override fun showContent(content: String?) {
            println("DreloveyService borrow content = $content")
            handler.post{
                Toast.makeText(this@DreloveyService,"showContent $content",Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onBind(p0: Intent?): IBinder {
        return myBinder
    }
}