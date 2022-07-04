package com.zheng.learn_android.ui.interview.handler

open class Handler {

    var looper: Looper? = null

    init {
        looper = Looper.myLooper()
    }

    //发送消息
    fun sendMessage(msg: Message) {
        enqueueMessage(msg)
    }

    fun enqueueMessage(msg: Message) {
        msg.target = this
        looper?.enqueueMessage(msg)
    }

    //处理消息
    open fun handleMessage(msg: Message) {

    }
}