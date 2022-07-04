package com.zheng.learn_android.ui.interview.handler

import java.lang.Exception
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue

class MessageQueue {
    val queue: BlockingQueue<Message> = ArrayBlockingQueue(100)

    fun enqueueMessage(msg: Message) {
        try {
            queue.put(msg)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun next(): Message? {
        var msg: Message? = null
        try {
            msg = queue.take()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return msg
    }
}