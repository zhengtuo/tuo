package com.mingtao.professionedu.ui.compose.login.model

import com.mingtao.professionedu.data.DataRepository
import com.zheng.lib.data.model.Resource
import javax.inject.Inject

/**
 * @Author: Drelovey
 * @CreateDate: 2020/1/22 14:46
 */
class MTPCLoginModel @Inject constructor(private val dataRepository: DataRepository) {

    suspend fun sendSms(phone: String, type: Int): Resource<Any> {
        return dataRepository.sendSms(phone, type)
    }

    suspend fun login(user_name: String, sms_code: String): Resource<Any> {
        return dataRepository.codePhone(user_name, sms_code)
    }
}