package com.mingtao.professionedu.ui.compose.main.model

import com.mingtao.professionedu.data.DataRepository
import com.zheng.base.data.model.Resource
import javax.inject.Inject

/**
 * @Author: Drelovey
 * @CreateDate: 2020/1/22 14:46
 */
class MTPCStudyModel @Inject constructor(private val dataRepository: DataRepository) {

    suspend fun getUserStudyInfo(): Resource<Any> {
        return dataRepository.getUserStudyInfo()
    }

}