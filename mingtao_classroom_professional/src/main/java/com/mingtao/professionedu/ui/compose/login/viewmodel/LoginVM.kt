package com.mingtao.professionedu.ui.compose.login.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.zheng.lib.base.model.EmptyModel
import com.zheng.lib.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginVM @Inject constructor(private val mModel: EmptyModel) : BaseViewModel() {
    //手机号码
    var phoneNumber by mutableStateOf("")

    //验证码
    var code by mutableStateOf("")
}