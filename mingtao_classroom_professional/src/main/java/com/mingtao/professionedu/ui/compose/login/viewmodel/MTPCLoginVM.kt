package com.mingtao.professionedu.ui.compose.login.viewmodel

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.alibaba.android.arouter.launcher.ARouter
import com.mingtao.professionedu.ui.compose.login.model.MTPCLoginModel
import com.zheng.base.utils.BaseUtils
import com.zheng.base.utils.launch
import com.zheng.base.viewmodel.BaseViewModel
import com.zheng.comon.arouter.RouterPath
import com.zheng.lib.data.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
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

    //60秒倒计时Job
    private var job: Job? = null

    //是否能发送验证码
    var smsEnable: Boolean = false

    //发送验证码按钮显示
    var time by mutableStateOf("获取验证码")

    //是否显示返回
    var showJump by mutableStateOf(false)

    fun canSendCode(): Boolean {
        return phoneNumber.length == 11
    }

    fun canLoginOrRegister(): Boolean {
        return phoneNumber.length == 11 && code.length == 6
    }

    //发送验证码
    fun sendCode() {
        if (smsEnable) {
            return
        }
        if (!canSendCode()) {
            Toast.makeText(BaseUtils.context, "请输入手机号码", Toast.LENGTH_SHORT).show()
            return
        }
        launch({
            dataLiveData.postValue(Resource.Loading())
            dataLiveData.postValue(withContext(Dispatchers.IO) {
                mModel.sendSms(phoneNumber, 2)
            })
        })
    }

    //登录/注册
    fun loginOrRegister() {
        if (!canLoginOrRegister()) {
            return
        }
        if (!isChecked()) {
            return
        }

        launch({
            dataLiveData.postValue(Resource.Loading())
            dataLiveData.postValue(withContext(Dispatchers.IO) {
                mModel.login(phoneNumber, code, "android", 1)
            })
        })
    }

    /**
     * 60秒倒计时
     */
    fun countDown() {
        job?.cancel()
        job = launch({
            flow {
                (59 downTo 0).forEach {
                    delay(1000)
                    emit(it)
                }
            }.flowOn(Dispatchers.Default).onStart { // 倒计时开始 ，在这里可以让Button 禁止点击状态
                smsEnable = false
                time = "60s后重新获取"
            }.onCompletion { // 倒计时结束 ，在这里可以让Button 恢复点击状态
                smsEnable = true
                time = "重新获取"
            }.collect { // 在这里 更新LiveData 的值来显示到UI
                time = it.toString() + "s后重新获取"
            }
        })
    }

    private fun isChecked(): Boolean {
        if (!checked) {
            Toast.makeText(BaseUtils.context, "请勾选下方的同意并愿意遵守名淘尚科《用户服务协议》、《用户付费协议》、《用户隐私协议》", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    fun goMain() {
        ARouter.getInstance().build(RouterPath.MTP_PATH_MAIN).navigation()
        finish()
    }
}