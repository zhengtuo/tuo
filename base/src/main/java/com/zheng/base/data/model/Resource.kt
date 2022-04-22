package com.zheng.base.data.model

/**
 * @Author: Drelovey
 * @CreateDate: 2020/1/20 17:07
 */
sealed class Resource<T>(
    var data: T? = null,
    val methodName: String? = null,
    val errorCode: Int? = null,
    val errorCase: String? = null,
    val timestamp: Long = 0
) {
    class Success<T>(data: T?, methodName: String?,timestamp:Long = 0) : Resource<T>(data, methodName, timestamp = timestamp)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Complete<T>(data: T? = null) : Resource<T>(data)
    class DataError<T>(errorCode: Int, errorCase: String?) :
        Resource<T>(null, null, errorCode, errorCase)
}