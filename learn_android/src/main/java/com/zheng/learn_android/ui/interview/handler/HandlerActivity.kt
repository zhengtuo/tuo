package com.zheng.learn_android.ui.interview.handler

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HandlerActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Looper.prepare()
        val handler = object : Handler() {
            override fun handleMessage(msg: Message) {
                //Toast.makeText(applicationContext,msg.any.toString(),Toast.LENGTH_SHORT).show()
                println(msg.any.toString())
            }
        }
        Thread {
            handler.sendMessage(Message("Drelovey Thread"))
        }.start()
        Looper.loop()
    }
}