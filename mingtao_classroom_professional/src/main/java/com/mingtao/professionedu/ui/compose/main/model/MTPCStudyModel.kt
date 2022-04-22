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

    suspend fun getUserCourse(withCatalog: Int, withLearnInfo: Int): Resource<Any> {
        return dataRepository.getUserCourse(withCatalog, withLearnInfo)
    }

    suspend fun getUserVideo(page: Int, pageSize: Int): Resource<Any> {
        return dataRepository.getUserVideo(page, pageSize)
    }

    suspend fun getUserQuestion(): Resource<Any> {
        return dataRepository.getUserQuestion()
    }

    suspend fun getUserBuyCourseTopics(courseId: Int): Resource<Any> {
        return dataRepository.getUserBuyCourseTopics(courseId)
    }

}