package com.zheng.learn_android.ui.interview

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.zheng.learn_android.databinding.LaActivityAidlBinding
import com.zt.server.Drelovey
import com.zt.server.ServerBean
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AIDLActivity:AppCompatActivity() {

    private var drelovey: Drelovey? = null
    private var serviceConnection: ServiceConnection? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding = LaActivityAidlBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.test.setOnClickListener {
            if (drelovey == null) {
                Toast.makeText(it.context, "未获取到binder对象", Toast.LENGTH_SHORT).show()
            } else {
                drelovey?.showContent("success")
                lifecycleScope.launch {
                    delay(5000)
                    ServerBean().apply {
                        id = 1
                        name = "success"
                        drelovey?.sendData(this)
                    }
                }
            }
        }

        serviceConnection = object : ServiceConnection {
            override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
                println("xxxx onServiceConnected p0 = [${p0}], p1 = [${p1}]")
                drelovey = Drelovey.Stub.asInterface(p1)
            }

            override fun onServiceDisconnected(p0: ComponentName?) {
                println("xxxx onServiceDisconnected p0 = [${p0}]")
            }
        }
        val ret = bindService(
            getServiceIntent(),
            serviceConnection as ServiceConnection,
            Service.BIND_AUTO_CREATE
        )
        if (!ret) {
            unbindService(serviceConnection as ServiceConnection)
        }
    }

    private fun getServiceIntent(): Intent {
        val intent = Intent("com.zt.server.DreloveyService")
        intent.setPackage("com.zt.server")
        return intent
    }
}