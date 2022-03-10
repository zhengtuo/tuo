package com.zheng.tuo.ui.application.mt.system.model

import com.zheng.lib.data.model.Resource
import com.zheng.tuo.data.DataRepository
import javax.inject.Inject

/**
 * @Author: Drelovey
 * @CreateDate: 2020/1/22 14:46
 */
class LoginModel @Inject constructor(private val dataRepository: DataRepository) {
    suspend fun getKey(): Resource<Any> {
        return dataRepository.getKey()
    }

    suspend fun sendSms(phone: String, type: Int, code: String): Resource<Any> {
        return dataRepository.sendSms(phone, type, code)
    }

    suspend fun login(user_name: String, sms_code: String): Resource<Any> {
        return dataRepository.codePhone(user_name, sms_code)
    }
}