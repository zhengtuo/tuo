@file:Suppress("unused")

package com.zheng.lib.base.viewmodel

import android.os.Message
import androidx.lifecycle.MutableLiveData
import com.zheng.lib.data.error.ErrorMapper
import com.skydoves.bindables.BindingViewModel
import com.zheng.lib.data.model.Resource
import com.zheng.lib.livedata.SingleLiveEvent
import com.zheng.lib.manager.errors.ErrorManager


open class BaseViewModel : BindingViewModel() {

    private var finishEvent: SingleLiveEvent<Void>? = null

    val dataLiveData: MutableLiveData<Resource<Any>> = MutableLiveData()

    open var errorMapper = ErrorMapper()

    val errorManager: ErrorManager
        get() = ErrorManager(
            errorMapper
        )

    open fun initData() {}

    /**
     * 关闭界面
     */
    open fun finish() {
        getFinishEvent().call()
    }

    open fun getFinishEvent(): SingleLiveEvent<Void> {
        return createLiveData(finishEvent).also { finishEvent = it }
    }

    protected open fun createLiveData(liveData: SingleLiveEvent<Void>?): SingleLiveEvent<Void> {
        if (liveData == null) {
            return SingleLiveEvent()
        }
        return liveData
    }

    /**
     * 提供自定义单一消息事件
     */
    private val mSingleLiveEvent: SingleLiveEvent<Message> = SingleLiveEvent()


    /**
     * 发送单个消息事件，消息为[Message]对象，可通过[Message.what]区分消息类型，用法与[Message]一致，
     */
    open fun sendSingleLiveEvent(what: Int) {
        val message = Message.obtain()
        message.what = what
        mSingleLiveEvent.value = message
    }

    open fun sendSingleLiveEvent(what: Int, `object`: Any?) {
        val message = Message.obtain()
        message.what = what
        message.obj = `object`
        mSingleLiveEvent.value = message
    }

    /**
     * 暴露给观察者提供接收单个消息事件，通过注册[registerSingleLiveEvent(Observer)]
     */
    open fun getSingleLiveEvent(): SingleLiveEvent<Message> {
        return mSingleLiveEvent
    }
}