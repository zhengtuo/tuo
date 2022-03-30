package com.mingtao.professionedu.data

import com.mingtao.professionedu.data.model.BaseEntity
import com.mingtao.professionedu.data.remote.service.ApiService
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import com.zheng.comon.utils.CommonUtils
import com.zheng.lib.data.error.Error.Companion.HAVE_MESSAGE
import com.zheng.lib.data.error.Error.Companion.NO_INTERNET_CONNECTION
import com.zheng.lib.data.error.Error.Companion.UN_KNOW
import com.zheng.lib.data.model.Resource
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

    suspend fun sendSms(phone: String, type: Int): Resource<Any> {
        return processCallByApi({
            dataGenerator.getRetrofitService(ApiService::class.java).sendSms(phone, type)
        }, "sendSms")
    }

    suspend fun codeLogin(loginName: String, code: String, platform: String, loginType: Int): Resource<Any> {
        return processCallByApi({
            dataGenerator.getRetrofitService(ApiService::class.java).codeLogin(loginName, code, platform, loginType)
        }, "codeLogin")
    }

    suspend fun passwordLogin(loginName: String, password: String, platform: String, loginType: Int): Resource<Any> {
        return processCallByApi({
            dataGenerator.getRetrofitService(ApiService::class.java).passwordLogin(loginName, password, platform, loginType)
        }, "passwordLogin")
    }


    private suspend fun processCallByApi(responseCall: suspend () -> ApiResponse<BaseEntity<*>>, methodName: String): Resource<Any> {
        var result: Resource<Any> = Resource.DataError(errorCode = UN_KNOW, null)
        if (!CommonUtils.checkNet()) {
            return Resource.DataError(errorCode = NO_INTERNET_CONNECTION, null)
        }
        val response = responseCall.invoke()

        response.suspendOnSuccess {
            result = if (data.code == 0) {
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