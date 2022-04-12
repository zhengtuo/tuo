package com.mingtao.professionedu.ui.compose.login.model

import com.mingtao.professionedu.data.DataRepository
import com.zheng.base.data.model.Resource
import javax.inject.Inject

/**
 * @Author: Drelovey
 * @CreateDate: 2020/1/22 14:46
 */
class MTPCFindPasswordModel @Inject constructor(private val dataRepository: DataRepository) {

    suspend fun sendSms(phone: String, type: Int): Resource<Any> {
        return dataRepository.sendSms(phone, type)
    }

    suspend fun login(loginName: String, code: String, platform: String, loginType: Int): Resource<Any> {
        return dataRepository.codeLogin(loginName, code, platform, loginType)
    }

    suspend fun changePassword(loginName: String, password: String, code: String, codeType: Int): Resource<Any> {
        return dataRepository.changePassword(loginName, password, code, codeType)
    }
}