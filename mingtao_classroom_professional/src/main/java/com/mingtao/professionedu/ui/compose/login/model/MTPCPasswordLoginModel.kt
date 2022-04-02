package com.mingtao.professionedu.ui.compose.login.model

import com.mingtao.professionedu.data.DataRepository
import com.zheng.lib.data.model.Resource
import javax.inject.Inject

/**
 * @Author: Drelovey
 * @CreateDate: 2020/1/22 14:46
 */
class MTPCPasswordLoginModel @Inject constructor(private val dataRepository: DataRepository) {

    suspend fun passwordLogin(loginName: String, code: String, platform: String, loginType: Int): Resource<Any> {
        return dataRepository.passwordLogin(loginName, code, platform, loginType)
    }
}