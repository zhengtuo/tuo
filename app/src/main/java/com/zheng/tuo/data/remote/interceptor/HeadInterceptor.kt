package com.zheng.tuo.data.remote.interceptor

import com.zheng.lib.utils.SharedPreferencesUtils
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

/**
 * @Author: Drelovey
 * @CreateDate: 2020/4/29 18:07
 */
class HeadInterceptor : Interceptor {

    private val CONTENT_TYPE = "Content-Type"
    private val CONTENT_TYPE_VALUE = "application/json"

    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        if ("POST" == original.method) {
            if (original.body is FormBody) {
                val bodyBuilder = FormBody.Builder()
                var formBody: FormBody = original.body as FormBody
                for (i in 0 until formBody.size) {
                    bodyBuilder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i))

                    Timber.i(
                        "key=%s , value = %s", formBody.encodedName(i), formBody.encodedValue(i)
                    )
                }
                // 添加公共参数
                formBody = bodyBuilder.addEncoded("Token", SharedPreferencesUtils.getString("token", "")).build()
                original = original.newBuilder().post(formBody).build()
            }
        } else if ("GET" == original.method) {
            val originalUrl = original.url
            val url = originalUrl.newBuilder()

                .build()
            original = original.newBuilder().url(url).method(original.method, original.body).build()
        }
        val request = original.newBuilder().header(CONTENT_TYPE, CONTENT_TYPE_VALUE).method(original.method, original.body).build()
        return chain.proceed(request)
    }
}