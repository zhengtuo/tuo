package com.zheng.tuo.data.remote

import com.google.gson.Gson
import com.zheng.tuo.BuildConfig
import com.zheng.tuo.data.remote.interceptor.HeadInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 *

 * @Author: Drelovey
 * @CreateDate: 2020/1/20 18:29
 */
class ServiceGenerator @Inject constructor(private val gson: Gson) {

    //Network constants
    private val TIMEOUT_CONNECT = 30   //In seconds
    private val TIMEOUT_READ = 30   //In seconds
    private val CONTENT_TYPE = "Content-Type"
    private val CONTENT_TYPE_VALUE = "application/json"

    private val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    private var retrofit: Retrofit? = null

    private val headInterceptor: HeadInterceptor = HeadInterceptor()
    private val logger: HttpLoggingInterceptor
        get() {
            val loggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                loggingInterceptor.apply {
                    loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
                }.level = HttpLoggingInterceptor.Level.BODY
            }
            return loggingInterceptor
        }

    init {
        okHttpBuilder.addInterceptor(headInterceptor)
        okHttpBuilder.addInterceptor(logger)
        okHttpBuilder.connectTimeout(TIMEOUT_CONNECT.toLong(), TimeUnit.SECONDS)
        okHttpBuilder.readTimeout(TIMEOUT_READ.toLong(), TimeUnit.SECONDS)
    }

    //!!.表示当前对象如果为空也执行，然后会抛出空异常
    fun <T> createService(serviceClass: Class<T>, baseUrl: String): T {
        val client = okHttpBuilder.build()
        retrofit = Retrofit.Builder().baseUrl(baseUrl).client(client)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()

        return retrofit!!.create(serviceClass)
    }
}