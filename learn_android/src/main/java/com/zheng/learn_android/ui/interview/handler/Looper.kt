package com.zheng.learn_android.ui.interview.handler

import java.lang.RuntimeException

class Looper {
    var mQueue: MessageQueue? = null

    init {
        mQueue = MessageQueue()
    }

    companion object {
        val sThreadLocal:ThreadLocal<Looper> = ThreadLocal()

        fun myLooper():Looper? {
            return  sThreadLocal.get()
        }

        fun prepare() {

            val looper = sThreadLocal.get()
            if (looper!=null) {
                throw RuntimeException("Only one Looper may be created per thread")
            }

            sThreadLocal.set(Looper())
        }

        //开启轮询
        fun loop() {
            val looper = sThreadLocal.get()
            val messageQueue = looper?.mQueue
            while (true) {
                val msg = messageQueue?.next()
                if (msg != null) {
                    msg.target?.handleMessage(msg)
                }
            }
        }
    }


    fun enqueueMessage(msg: Message) {
        mQueue?.enqueueMessage(msg)
    }


}