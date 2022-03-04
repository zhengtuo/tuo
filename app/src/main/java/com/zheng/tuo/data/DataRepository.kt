package com.zheng.tuo.data


import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import com.zheng.lib.data.DataGenerator
import com.zheng.lib.data.error.Error.Companion.HAVE_MESSAGE
import com.zheng.lib.data.error.Error.Companion.NO_INTERNET_CONNECTION
import com.zheng.lib.data.error.Error.Companion.UN_KNOW
import com.zheng.lib.data.model.Resource
import com.zheng.lib.utils.LibUtils
import com.zheng.tuo.data.model.BaseEntity
import com.zheng.tuo.data.remote.service.ApiService
import timber.log.Timber
import javax.inject.Inject

/**
 *

 * @Author: Drelovey
 * @CreateDate: 2020/1/20 16:44
 */
class DataRepository @Inject constructor(
    private val dataGenerator: DataGenerator,
) {


    suspend fun getKey(): Resource<Any> {
        return processCallByApi(
            {
                dataGenerator.getRetrofitService(ApiService::class.java).getKey()
            }, "getKey"
        )
    }

    suspend fun sendSms(phone: String, type: Int, code: String): Resource<Any> {
        return processCallByApi(
            {
                dataGenerator.getRetrofitService(ApiService::class.java).sendSms(
                    phone, type, code
                )
            }, "sendSms"
        )
    }

    suspend fun codePhone(user_name: String, sms_code: String): Resource<Any> {
        return processCallByApi(
            {
                dataGenerator.getRetrofitService(ApiService::class.java).codeLogin(
                    user_name, sms_code
                )
            }, "codeLogin"
        )
    }

    private suspend fun processCallByApi(
        responseCall: suspend () -> ApiResponse<BaseEntity<*>>, methodName: String
    ): Resource<Any> {
        var result: Resource<Any> = Resource.DataError(errorCode = UN_KNOW, null)
        if (!LibUtils.checkNet()) {
            return Resource.DataError(errorCode = NO_INTERNET_CONNECTION, null)
        }
        val response = responseCall.invoke()

        response.suspendOnSuccess {
            result = if (data.status) {
                Resource.Success(data = data.data, methodName = methodName)
            } else {
                Resource.DataError(errorCode = HAVE_MESSAGE, errorCase = data.msg)
            }
        }
        response.suspendOnError {
            result = Resource.DataError(errorCode = raw.code, errorCase = raw.message)
        }

        response.suspendOnFailure {
            result = Resource.DataError(errorCode = UN_KNOW, null)
            Timber.tag(methodName).d(this)
        }

        return result
    }

}