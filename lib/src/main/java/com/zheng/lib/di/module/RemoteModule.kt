@file:Suppress("unused")

package com.zheng.lib.di.module

import android.app.Application
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import com.skydoves.whatif.whatIf
import com.zheng.lib.data.model.NetConfig
import com.zheng.lib.utils.LibUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @Author: Drelovey
 * @CreateDate: 2020/4/29 16:53
 */
@Suppress("JoinDeclarationAndAssignment", "UNUSED_PARAMETER", "ObjectLiteralToLambda")
@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    lateinit var netConfig: NetConfig

    private fun initConfig() {
        netConfig = LibUtils.netConfig ?: NetConfig()
    }


    @Provides
    @Singleton
    fun provideBaseRetrofit(
        builder: Retrofit.Builder,
        client: OkHttpClient,
    ): Retrofit {
        if (netConfig.BASE_URL == null) {
            throw NullPointerException("place set base url")
        }
        return createRetrofit(builder, client, netConfig.BASE_URL!!)
    }

    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
    }

    @Singleton
    @Provides
    fun provideOkHttpBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.d("LoggingInterceptor: %s", message)
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideCache(application: Application): Cache {
        initConfig()
        val cacheFile = File(application.cacheDir, netConfig.PATH_CACHE)
        return Cache(cacheFile, netConfig.CACHE_SIZE)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        builder: OkHttpClient.Builder, loggingInterceptor: HttpLoggingInterceptor, cache: Cache
    ): OkHttpClient {
        return builder
            //连接超时时间（默认10秒）
            .connectTimeout(netConfig.DEFAULT_TIME_OUT, TimeUnit.SECONDS)
            //读取超时时间
            .readTimeout(netConfig.DEFAULT_TIME_OUT, TimeUnit.SECONDS)
            //写超时时间
            .writeTimeout(netConfig.DEFAULT_TIME_OUT, TimeUnit.SECONDS)
            //.retryOnConnectionFailure(true)
            .whatIf(netConfig.interceptor != null) {
                addInterceptor(netConfig.interceptor!!)
            }.addInterceptor(loggingInterceptor)
            //.cache(cache)
            .build()
    }

    private fun createRetrofit(
        builder: Retrofit.Builder,
        okhttpClient: OkHttpClient,
        url: String,
    ): Retrofit {
        return builder.client(okhttpClient).baseUrl(url)
            //kotlin
            .addConverterFactory(MoshiConverterFactory.create()).addCallAdapterFactory(CoroutinesResponseCallAdapterFactory.create()).build()
    }

}