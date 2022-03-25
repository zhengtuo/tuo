package com.mingtao.professionedu.ui.compose.login.viewmodel

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import com.mingtao.professionedu.ui.compose.login.model.MTPCLoginModel
import com.zheng.lib.base.viewmodel.BaseViewModel
import com.zheng.lib.data.model.Resource
import com.zheng.lib.utils.LibUtils
import com.zheng.lib.utils.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MTPCLoginVM @Inject constructor(private val mModel: MTPCLoginModel) : BaseViewModel() {
    //手机号码
    var phoneNumber by mutableStateOf("")

    //验证码
    var code by mutableStateOf("")

    //协议
    var checked by mutableStateOf(false)

    var loginLiveData: MutableLiveData<Resource<Any>> = MutableLiveData()

    fun canSendCode(): Boolean {
        return phoneNumber.length == 11
    }

    fun canLoginOrRegister(): Boolean {
        return phoneNumber.length == 11 && code.length == 6
    }

    suspend fun sendCode() {
        if (!canSendCode()) {
            Toast.makeText(LibUtils.context, "请输入手机号码", Toast.LENGTH_SHORT).show()
            return
        }
        launch({
            loginLiveData.postValue(withContext(Dispatchers.IO) {
                mModel.sendSms(phoneNumber, 2)
            })
        })
    }
}