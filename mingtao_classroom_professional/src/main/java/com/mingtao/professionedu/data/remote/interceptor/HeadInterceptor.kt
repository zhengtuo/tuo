package com.mingtao.professionedu.data.remote.interceptor

import com.zheng.comon.utils.MD5Utils
import com.zheng.lib.utils.LibUtils
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

/**
 * @Author: Drelovey
 * @CreateDate: 2020/4/29 18:07
 */
@Suppress("PrivatePropertyName")
class HeadInterceptor : Interceptor {

    private val CONTENT_TYPE = "Content-Type"
    private val CONTENT_TYPE_VALUE = "application/json"

    private val KEY = "eL3ZuSbj3mGCqNN5QiCqLmYlkEeJhZlv"

    private val PHONE_INFO: String = LibUtils.getDeviceBrand() + ":" + LibUtils.getDeviceModel() + " Android:" + LibUtils.getSystemVersion() + " professionedu:V:" + LibUtils.getVersionName()

    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val currMillis = System.currentTimeMillis()
        //服务器token
        val token = MD5Utils.md5(KEY+currMillis)
        if ("POST" == original.method) {
            if (original.body is FormBody) {
                val bodyBuilder = FormBody.Builder()
                var formBody: FormBody = original.body as FormBody
                for (i in 0 until formBody.size) {
                    bodyBuilder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i))
                    Timber.i("key=%s , value = %s", formBody.encodedName(i), formBody.encodedValue(i))
                }
                // 添加公共参数
                formBody = bodyBuilder
                    .build()

                Timber.i("PHONE_INFO:$PHONE_INFO")
                original = original.newBuilder().post(formBody).build()
            }
        } else if ("GET" == original.method) {
            val originalUrl = original.url
            val url = originalUrl.newBuilder()
                .build()
            original = original.newBuilder().url(url).method(original.method, original.body).build()
        }
        val request = original
            .newBuilder()
            .header(CONTENT_TYPE, CONTENT_TYPE_VALUE)
            .addHeader("token", token)
            .addHeader("timestamp", currMillis.toString())
            .addHeader("phoneInfo", PHONE_INFO)
            .addHeader("sourceFrom", "3")
            .addHeader("clientSource", "android")
            .method(original.method, original.body)
            .build()
        return chain.proceed(request)
    }
}