package com.mingtao.professionedu.ui.compose.login.viewmodel

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.mingtao.professionedu.ui.compose.login.model.MTPCPasswordLoginModel
import com.zheng.base.utils.BaseUtils
import com.zheng.base.utils.launch
import com.zheng.base.viewmodel.BaseViewModel
import com.zheng.comon.utils.SharedPreferencesUtils
import com.zheng.lib.data.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MTPCPasswordLoginVM @Inject constructor(private val mModel: MTPCPasswordLoginModel) : BaseViewModel() {
    //手机号码
    var phoneNumber by mutableStateOf("")

    //密码
    var password by mutableStateOf("")

    //协议
    var checked by mutableStateOf(false)

    //记住密码
    var rememberChecked by mutableStateOf(SharedPreferencesUtils.getBoolean("pw_remember", false))

    //密码是否密文
    var isCiphertext by mutableStateOf(true)

    init {
        if (SharedPreferencesUtils.getBoolean("pw_remember", false)) {
            phoneNumber = SharedPreferencesUtils.getString("phone", "")
            password = SharedPreferencesUtils.getString("userPassword", "")
        }
    }


    fun canLogin(): Boolean {
        return phoneNumber.length == 11 && password.length >= 6
    }

    //登录
    fun login() {
        if (!canLogin()) {
            return
        }
        if (!isChecked()) {
            return
        }

        launch({
            dataLiveData.postValue(Resource.Loading())
            dataLiveData.postValue(withContext(Dispatchers.IO) {
                mModel.passwordLogin(phoneNumber, password, "android", 0)
            })
        })
    }

    private fun isChecked(): Boolean {
        if (!checked) {
            Toast.makeText(BaseUtils.context, "请勾选下方的同意并愿意遵守名淘尚科《用户服务协议》、《用户付费协议》、《用户隐私协议》", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

}