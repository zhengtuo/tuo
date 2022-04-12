package com.mingtao.professionedu.ui.compose.main.model

import com.mingtao.professionedu.data.DataRepository
import com.zheng.base.data.model.Resource
import javax.inject.Inject

/**
 * @Author: Drelovey
 * @CreateDate: 2020/1/22 14:46
 */
class MTPCHomeModel @Inject constructor(private val dataRepository: DataRepository) {

    suspend fun getHotSearchKeyword(): Resource<Any> {
        return dataRepository.getHotSearchKeyword()
    }

    suspend fun getBannerList(position: String): Resource<Any> {
        return dataRepository.getBannerList(position)
    }

    suspend fun getHomeType(): Resource<Any> {
        return dataRepository.getHomeType()
    }

    suspend fun getArticleList(page: Int, pageSize: Int): Resource<Any> {
        return dataRepository.getArticleList(page, pageSize)
    }

    suspend fun getHomeFloorModule(page: Int, pageSize: Int): Resource<Any> {
        return dataRepository.getHomeFloorModule(page, pageSize)
    }
}