package com.zheng.network.model

import okhttp3.Interceptor

@Suppress("PropertyName")
class NetConfig {

    var PATH_CACHE = "cache_path"
    var CACHE_SIZE = (1024 * 1024 * 50).toLong()

    /**
     * 默认超时时间,单位:秒
     */
    var DEFAULT_TIME_OUT: Long = 15

    //基础地址
    var BASE_URL: String? = null

    //拦截器
    var interceptor: Interceptor? = null


    fun setBaseUrl(baseUrl: String): NetConfig {
        BASE_URL = baseUrl
        return this
    }

    fun setInterceptor(interceptor: Interceptor): NetConfig {
        this.interceptor = interceptor
        return this
    }

    fun setTimeOut(timeOut: Long): NetConfig {
        this.DEFAULT_TIME_OUT = timeOut
        return this
    }
}