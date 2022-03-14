package com.mingtao.professionedu.data.remote.service

import com.mingtao.professionedu.data.model.BaseEntity
import com.mingtao.professionedu.data.model.KeyBean
import com.mingtao.professionedu.data.model.LoginBean
import com.skydoves.sandwich.ApiResponse

import retrofit2.http.*

/**
 *

 * @Author: Drelovey
 * @CreateDate: 2020/1/21 11:12
 */
interface ApiService {

    /**
     * 获取验证信息(正确才能发送验证码)
     */
    @POST("api/user/key")
    suspend fun getKey(): ApiResponse<BaseEntity<KeyBean>>


    /**
     * 发送短信验证码
     */
    @POST("api/user/Ajax_sendSMS")
    @FormUrlEncoded
    suspend fun sendSms(
        @Field("mobile") mobile: String, @Field("type") type: Int, @Field("code") code: String
    ): ApiResponse<BaseEntity<Any>>

    @POST("api/Index/login_sms")
    @FormUrlEncoded
    suspend fun codeLogin(
        @Field("user_name") user_name: String, @Field("sms_code") sms_code: String
    ): ApiResponse<BaseEntity<LoginBean>>


}