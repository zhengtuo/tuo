package com.zheng.learn_android.ui.interview.handler

class Message {
    var any: Any? = null

    var target: Handler? = null

    constructor(data: String) {
        any = data
    }

}